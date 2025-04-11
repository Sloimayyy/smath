package com.sloimay.smathcodegen.vec

import com.sloimay.smathcodegen.*
import com.sloimay.smathcodegen.codefile.*
import com.sloimay.smathcodegen.codefile.params.ClassParam
import com.sloimay.smathcodegen.codefile.params.FunParam
import java.util.*


private val LOCALE = Locale.UK



class VecClassFileGen(
    vecToGen: VecToGen,
) {

    val vecName: String = vecToGen.vecName
    val compType: String = vecToGen.compType
    val dims: Int = vecToGen.dims
    val className = vecToGen.className

    val compNames = COORDS_NAME.toList().slice(0 until dims)

    val codeFile = CodeFile(
        "$className.kt",
        "com.sloimay.smath.vectors",
        "vectors"
    )

    val companionRepr = CompanionObjectRepr()

    val vecClassRepr = ClassRepr(listOf("data"), className, compNames.map { ClassParam(it, compType, null, listOf("val")) }
    ).also {
        /*for (compName in compNames) {
            it.addElem(
                ValDeclarationRepr(
                    listOf(),
                    false,
                    compName,
                    compType,
                )
            )
        }*/
        it.addElem(companionRepr)
    }


    fun make() {

        codeFile.addElem(vecClassRepr)


        // Companion
        companionFuncs()
        constants()

        // Main class body
        constructors()
        piecewiseBinaryOps()
        piecewiseUnaryOps()
        specialFuncs()
        swizzles()


        funConstructors()



        codeFile.writeToDisk()
    }


}








private fun VecClassFileGen.piecewiseBinaryOps() {
    piecewiseBinaryOp("plus", "+")
    piecewiseBinaryOp("minus", "-")
    piecewiseBinaryOp("times", "*")
    piecewiseBinaryOp("div", "/")
    piecewiseBinaryOp("rem", "%")
}

private fun VecClassFileGen.piecewiseUnaryOps() {
    piecewiseUnaryOp("unaryPlus", "+")
    piecewiseUnaryOp("unaryMinus", "-")
}


private fun VecClassFileGen.companionFuncs() {

    // Splat
    companionRepr.addElem(
        MethodRepr(
            listOf(),
            "splat",
            listOf(FunParam("value", compType)),
            true,
        ).also {
            it.addElem { code, context ->
                code.append("$className(${compNames.joinToString(", ") { "value" }})")
            }
        }
    )

    // New
    companionRepr.addElem(
        MethodRepr(
            listOf(),
            "new",
            compNames.map { FunParam(it, compType) },
            true,
        ).also {
            it.addElem { code, context ->
                code.append("$className(${compNames.joinToString(", ") { "$it" }})")
            }
        }
    )

}


private fun VecClassFileGen.constants() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    for ((compIdx, compName) in compNames.withIndex()) {
        val constName = compName.uppercase()

        constant(
            constName,
            "$className(${
                compNames.slice(0 until dims).mapIndexed { index, _ -> numStrToTyped(if (index != compIdx) "0" else "1", compType) }
                    .joinToString(", ")
            })",
            className
        )
    }

    constant(
        "ZERO",
        "$className(${
            compNames.slice(0 until dims).mapIndexed { index, _ -> numStrToTyped("0", compType) }
                .joinToString(", ")
        })",
        className
    )

    constant(
        "ONE",
        "$className(${
            compNames.slice(0 until dims).mapIndexed { index, _ -> numStrToTyped("1", compType) }
                .joinToString(", ")
        })",
        className
    )

    // Positive infinity and Negative infinity
    if (typeData.isFloatNum()) {
        for ((polarity, varName) in listOf(Pair("positive", "INF"), Pair("negative", "NEG_INF"))) {
            constant(
                varName,
                "splat(${compType}.${polarity.uppercase(LOCALE)}_INFINITY)",
                className
            )
        }
    }


}

private fun VecClassFileGen.swizzles() {
    val minDim = VECS_TO_GEN.minOfOrNull { it.dims }!!
    val maxDim = VECS_TO_GEN.maxOfOrNull { it.dims }!!

    for (d in minDim..maxDim) {
        val resultVecs = getVecsToGenWithDimsAndName(vecName, d)
        if (resultVecs.size > 1) error("Swizzle generation encountered more than 1 possible result type, which should be impossible.")
        if (resultVecs.isEmpty()) continue // don't generate this swizzle if the resulting vector doesnt exist
        val resultVec = resultVecs[0]

        val inputSpaces = (0 until resultVec.dims).map { compNames }
        for (swizzleList in cartesianProd(inputSpaces)) {
            val swizzleStr = swizzleList.joinToString("")

            vecClassRepr.addElem { code, context ->
                code.append(indentStr(context.indent))
                code.append(
                    "val $swizzleStr get() = ${resultVec.className}(${swizzleStr.toCharArray().joinToString { it.toString() }})"
                )

                code.append("\n")
            }
        }
    }


}

private fun VecClassFileGen.constructors() {

    for (t in NUM_TYPES) {
        for (d in listOf(1, dims)) {
            if (d == 1) {
                vecClassRepr.addElem { code, context ->
                    val indentStr = indentStr(context.indent)
                    code.append("${indentStr}constructor(")
                    code.append("value: ${t.name}")
                    code.append(")")
                    code.append(" : this(")
                    code.append(
                        compNames.slice(0 until dims).joinToString(", ") {
                            maybeApplyMemberFunc("value",
                                getNumConvData(t.name, compType).convStr,
                                false, t.name != compType)
                        }
                    )
                    code.append(")\n")
                }
            } else if (t.name != compType) {
                vecClassRepr.addElem { code, context ->
                    val indentStr = indentStr(context.indent)
                    code.append("${indentStr}constructor(")
                    code.append(compNames.joinToString { "$it: ${t.name}" })
                    code.append(")")
                    code.append(" : this(")
                    code.append(
                        compNames.slice(0 until dims).joinToString(", ") {
                            maybeApplyMemberFunc(it,
                                getNumConvData(t.name, compType).convStr,
                                false, t.name != compType)
                        }
                    )
                    code.append(")\n")
                }
            }
        }
    }

    // Empty constructor (the zero constructor)
    vecClassRepr.addElem { code, context ->
        val indentStr = indentStr(context.indent)
        code.append("${indentStr}constructor(")
        code.append(")")
        code.append(" : this(")
        code.append( compNames.joinToString { numStrToTyped("0", compType) } )
        code.append(")\n")
    }
}


private fun VecClassFileGen.funConstructors() {

    // Function constructors with dims >= 1
    for (t in NUM_TYPES) {
        for (d in listOf(1, dims)) {
            codeFile.addElem(
                MethodRepr(
                    listOf(),
                    className.lowercase(LOCALE),
                    if (d == 1) {
                        (0 until d).map { FunParam("value", t.name, ) }
                    } else {
                        (0 until d).map { FunParam(compNames[it], t.name, ) }
                    },
                    true,
                ).also {
                    it.addElem { code, context ->
                        code.append("$className(${compNames.slice(0 until d).joinToString(", ") {
                            maybeApplyMemberFunc(if (d!=1) "$it" else "value", 
                                getNumConvData(t.name, compType).convStr, 
                                false, t.name != compType)
                        }})")
                    }
                }
            )
        }
    }

    // Function constructor with dims == 0
    codeFile.addElem(
        MethodRepr(
            listOf(),
            className.lowercase(LOCALE),
            listOf(),
            true,
        ).also {
            it.addElem { code, context -> code.append("$className()") }
        }
    )

}


private fun VecClassFileGen.specialFuncs() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    vecClassRepr.addElem(
        MethodRepr(listOf(), "eq", listOf(FunParam("other", className)), true)
            .also { it.addElem { code, context ->
                    code.append("${compNames.joinToString(" && ") { "$it == other.$it" }}")
                }
            }
    )

}










private fun VecClassFileGen.constant(
    constName: String,
    constVal: String,
    constType: String,
) {
    companionRepr.addElem(
        ValDeclarationRepr(
            listOf(),
            false,
            constName,
            constType,
            constVal,
        )
    )
}


private fun VecClassFileGen.piecewiseUnaryOp(opName: String, opSymbol: String) {
    var body = "$className(${compNames.joinToString(", ") { "$opSymbol$it" }})"
    if (opName == "unaryPlus") {
        body = "this"
    }
    if (opName == "unaryMinus" && NAMES_TO_NUM_TYPES[compType]!!.isUnsigned()) return
    vecClassRepr.addElem(
        MethodRepr(
            listOf("operator"),
            opName,
            listOf(),
            true
        ).also {
            it.addElem { code, context ->
                code.append(body)
            }
        }
    )
}


private fun VecClassFileGen.piecewiseBinaryOp(opName: String, opSymbol: String) {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!
    val extraConversionNeeded = typeData.easilyNeedsReconversion() && (opName in
            listOf("plus", "minus", "times", "div", "rem")
    )

    vecClassRepr.addElem(
        MethodRepr(
            listOf("operator"),
            opName,
            listOf(FunParam("other", className)),
            true,
        ).also {
            it.addElem { code, context ->
                code.append("$className(${compNames.joinToString(", ") {
                    maybeApplyMemberFunc(
                        "$it $opSymbol other.$it",
                        getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr,
                        extraConversionNeeded, extraConversionNeeded
                    )
                }})")
            }
        }
    )

    vecClassRepr.addElem(
        MethodRepr(
            listOf("operator"),
            opName,
            listOf(FunParam("other", compType)),
            true,
        ).also {
            it.addElem { code, context ->
                code.append("$className(${compNames.joinToString(", ") {
                    maybeApplyMemberFunc(
                        "$it $opSymbol other",
                        getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr,
                        extraConversionNeeded, extraConversionNeeded
                    )
                }})")
            }
        }
    )

    codeFile.addElem(
        MethodRepr(
            listOf("operator"),
            "$compType.$opName",
            listOf(FunParam("other", className)),
            true,
        ).also {
            it.addElem { code, context ->
                code.append("$className(${compNames.joinToString(", ") {
                    maybeApplyMemberFunc(
                        "this $opSymbol other.$it",
                        getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr,
                        extraConversionNeeded, extraConversionNeeded
                    )
                }})")
            }
        }
    )


}
