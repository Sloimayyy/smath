package com.sloimay.smathcodegen.vecfile

import com.sloimay.smathcodegen.*
import com.sloimay.smathcodegen.codefile.CodeFileOld
import com.sloimay.smathcodegen.codefile.params.FunParam
import java.util.*


private val LOCALE = Locale.UK



class VecExtensionsFileGenOld(
    vecToGen: VecToGen,
) {
    val vecName: String = vecToGen.vecName
    val compType: String = vecToGen.compType
    val dims: Int = vecToGen.dims
    val className = vecToGen.className

    val compNames = COORDS_NAME.toList().slice(0 until dims)

    val codeFile = CodeFileOld(
        "$className.kt",
        "com.sloimay.smath.vectors.${className}Impl",
        "vectors"
    )

    fun make() {

        val code = codeFile.code


        imports()
        code.append("\n")

        piecewiseBinaryOps()
        code.append("\n")

        piecewiseUnaryOps()
        code.append("\n")

        constants()
        code.append("\n")

        specialFuncs()
        code.append("\n")

        factories()
        code.append("\n")

        casts()
        code.append("\n")


        codeFile.writeToDisk()
    }



}






private fun VecExtensionsFileGenOld.imports() {
    for (vecToGen in VECS_TO_GEN) {
        codeFile.code.append("import com.sloimay.smath.vectors.${vecToGen.className}\n")
    }
    codeFile.code.append("\n")
}





private fun VecExtensionsFileGenOld.piecewiseBinaryOps() {
    piecewiseBinaryOp("plus", "+")
    codeFile.code.append("\n")
    piecewiseBinaryOp("minus", "-")
    codeFile.code.append("\n")
    piecewiseBinaryOp("times", "*")
    codeFile.code.append("\n")
    piecewiseBinaryOp("div", "/")
    codeFile.code.append("\n")
    piecewiseBinaryOp("rem", "%")
    codeFile.code.append("\n")
}


private fun VecExtensionsFileGenOld.piecewiseUnaryOps() {
    piecewiseUnaryOp("unaryPlus", "+")
    piecewiseUnaryOp("unaryMinus", "-")
    codeFile.code.append("\n")
}



private fun VecExtensionsFileGenOld.specialFuncs() {

    val typeData = NAMES_TO_NUM_TYPES[compType]!!



    // Eq
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "eq", listOf(FunParam("other", className)), "Boolean",
        "${compNames.joinToString(" && ") { "$it == other.$it" }}"
    )

    // Abs
    if (!typeData.isUnsigned()) {
        if (typeData.easilyNeedsReconversion()) {
            val convStr = getNumConvData(typeData.name, "Int").convStr
            val invConvStr = getNumConvData("Int", typeData.name).convStr
            codeFile.oneLinerExtensionFunc(
                listOf(), className, "abs", listOf(), className,
                "$className(${compNames.joinToString(", ") { "kotlin.math.abs($it$convStr)$invConvStr" }})"
            )
        } else {
            codeFile.oneLinerExtensionFunc(
                listOf(), className, "abs", listOf(), className,
                "$className(${compNames.joinToString(", ") { "kotlin.math.abs($it)" }})"
            )
        }
    }

    // Mod
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "mod", listOf(
            FunParam("v", compType)
        ), className,
        "$className(${compNames.joinToString(", ") {
            "$it.mod(v)"
        }})"
    )

    // WithXYZW..
    for ((compIndex, compName) in compNames.withIndex()) {
        codeFile.oneLinerExtensionFunc(
            listOf(), className, "with${compName.uppercase(LOCALE)}", listOf(
                FunParam("value", compType)
            ), className,
            "$className(${compNames.withIndex().joinToString(", ") {
                "${if (compIndex == it.index) "value" else it.value}"
            }})"
        )
    }

    // Min, Max
    for (funcName in listOf("min", "max")) {
        val convStr = getNumConvData(typeData.name, if (typeData.isUnsigned()) "UInt" else "Int").convStr
        val invConvStr = getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", typeData.name).convStr
        val applyConvs = typeData.easilyNeedsReconversion()

        codeFile.oneLinerExtensionFunc(
            listOf(), className, funcName, listOf(
                FunParam("other", className)
            ), className,
            "$className(${compNames.joinToString(", ") {
                maybeApplyMemberFunc("kotlin.math.$funcName(${
                    maybeApplyMemberFunc(it, convStr, false, applyConvs)
                }, other.${
                    maybeApplyMemberFunc(it, convStr, false, applyConvs)
                })", invConvStr, false, applyConvs)
            }})"
        )
    }

    // Clamp
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "clamp", listOf(
            FunParam("min", className), FunParam("max", className)
        ), className,
        "max(min).min(max)"
    )

    // Dot
    run {
        val startType = if (typeData.isUnsigned()) "UInt" else "Int"
        val convStr = getNumConvData(startType, compType).convStr

        codeFile.oneLinerExtensionFunc(
            listOf(), className, "dot", listOf(
                FunParam("other", className)
            ), compType,
            maybeApplyMemberFunc("${compNames.joinToString(" + ") {
                "$it * other.$it"
            }}", convStr, true, typeData.easilyNeedsReconversion())
        )
    }

    // Length Sqrd
    for (alias in listOf("lenSq", "magSq")) {
        codeFile.oneLinerExtensionFunc(
            listOf(), className, alias, listOf(), compType,
            "dot(this)"
        )
    }

    // Dist
    run {
        for ((mainFuncName, lenFuncName) in listOf(Pair("dist", "len"), Pair("distSq", "lenSq"))) {
            val convertStr = if (typeData.isFloatNum()) ""
            else if (typeData.bits < 32) ".toVec3()"
            else ".toDVec3()"

            codeFile.oneLinerExtensionFunc(
                listOf(), className, mainFuncName, listOf(FunParam("other", className)), compType,
                "(this${convertStr} - other${convertStr}).$lenFuncName()"
            )
        }
    }


    if (typeData.isFloatNum()) {
        specialFloatFuncs()
    }




    codeFile.code.append("\n")
}

private fun VecExtensionsFileGenOld.specialFloatFuncs() {
    // Floor
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "floor", listOf(), className,
        "$className(${compNames.joinToString(", ") { "kotlin.math.floor($it)" }})"
    )

    // Ceil
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "ceil", listOf(), className,
        "$className(${compNames.joinToString(", ") { "kotlin.math.ceil($it)" }})"
    )

    // Round
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "round", listOf(), className,
        "$className(${compNames.joinToString(", ") { "kotlin.math.round($it)" }})"
    )

    // Fract
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "fract", listOf(), className,
        "mod(${numStrToTyped("1", compType)})"
    )

    // Len
    codeFile.oneLinerExtensionFunc(
        listOf(), className, "len", listOf(), compType,
        "kotlin.math.sqrt(lenSq())"
    )
}



private fun VecExtensionsFileGenOld.constants() {

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
        codeFile.code.append("\n")
    }

    constant(
        "ZERO",
        "$className(${
            compNames.slice(0 until dims).mapIndexed { index, _ -> numStrToTyped("0", compType) }
                .joinToString(", ")
        })",
        className
    )
    codeFile.code.append("\n")
    constant(
        "ONE",
        "$className(${
            compNames.slice(0 until dims).mapIndexed { index, _ -> numStrToTyped("1", compType) }
                .joinToString(", ")
        })",
        className
    )

    codeFile.code.append("\n")
}

private fun VecExtensionsFileGenOld.factories() {

    for (t in NUM_TYPES) {
        codeFile.oneLinerFunc(
            listOf(),
            className.lowercase(LOCALE),
            (0 until dims).map { FunParam(compNames[it], t.name) },
            className,
            "$className(${compNames.joinToString(", ") {
                /*var s = "$it"
                if (t.name != compType) {
                    s += ".to$compType()"
                }
                s*/
                maybeApplyMemberFunc("$it", getNumConvData(t.name, compType).convStr, false, t.name != compType)
            }})"
        )
    }

    codeFile.code.append("\n")
}

private fun VecExtensionsFileGenOld.casts() {

    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    for (vecToGen in VECS_TO_GEN) {
        if (className == vecToGen.className) continue

        codeFile.oneLinerExtensionFunc(
            listOf(),
            className,
            "to${vecToGen.className}",
            listOf(),
            vecToGen.className,
            "${vecToGen.className}(${compNames.joinToString(", ") {
                /*var s = "$it"
                s += ".to${vecToGen.compType}()"
                s*/
                applyMemberFunc("$it", getNumConvData(compType, vecToGen.compType).convStr, false)
            }})"
        )

    }

}





private fun VecExtensionsFileGenOld.constant(
    constName: String,
    constVal: String,
    constType: String,
) {
    codeFile.variableDeclaration(
        listOf("private"),
        "val",
        "CONST_${constName.uppercase(LOCALE)}",
        constType,
        constVal,
    )

    val code = codeFile.code

    code.append("val $className.Companion.$constName: $constType\n")
    code.append("    get() = CONST_${constName}\n")
}








private fun VecExtensionsFileGenOld.piecewiseBinaryOp(opName: String, opSymbol: String) {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!
    val extraConversionNeeded = typeData.easilyNeedsReconversion() && (opName in
            listOf("plus", "minus", "times", "div", "rem")
    )

    codeFile.oneLinerExtensionFunc(
        listOf("operator"),
        className,
        opName,
        listOf( FunParam("other", className) ),
        className,
        "$className(${compNames.joinToString(", ") {
            maybeApplyMemberFunc(
                "$it $opSymbol other.$it",
                getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr, 
                extraConversionNeeded, extraConversionNeeded
            ) 
        }})",
    )

    codeFile.oneLinerExtensionFunc(
        listOf("operator"),
        className,
        opName,
        listOf( FunParam("other", compType) ),
        className,
        "$className(${compNames.joinToString(", ") {
            maybeApplyMemberFunc(
                "$it $opSymbol other",
                getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr,
                extraConversionNeeded, extraConversionNeeded
            )
        }})",
    )

    codeFile.oneLinerExtensionFunc(
        listOf("operator"),
        compType,
        opName,
        listOf( FunParam("other", className) ),
        className,
        "$className(${compNames.joinToString(", ") {
            maybeApplyMemberFunc(
                "other.$it $opSymbol this",
                getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", compType).convStr,
                extraConversionNeeded, extraConversionNeeded
            )
        }})",
    )
}


private fun VecExtensionsFileGenOld.piecewiseUnaryOp(opName: String, opSymbol: String) {
    codeFile.oneLinerExtensionFunc(
        listOf("operator"),
        className,
        opName,
        listOf(),
        className,
        "$className(${compNames.joinToString(", ") { "$opSymbol$it" }})",
    )
}

