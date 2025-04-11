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
        it.writeStr("\n")
        it.addElem(companionRepr)
    }


    fun make() {




        // Imports
        imports()
        codeFile.writeStr("\n")
        codeFile.addElem(vecClassRepr)
        vecClassRepr.writeStr("\n")

        // Companion
        companionFuncs()
        companionRepr.writeStr("\n")
        constants()


        // Main class body
        constructors()
        vecClassRepr.writeStr("\n")
        codeFile.writeStr("\n") // "\n" after the class
        piecewiseBinaryOps()
        codeFile.writeStr("\n")
        piecewiseUnaryOps()
        getOperator()
        vecClassRepr.writeStr("\n")
        casts()
        vecClassRepr.writeStr("\n")
        specialFuncs()
        vecClassRepr.writeStr("\n")
        swizzles()
        vecClassRepr.writeStr("\n")
        toStringFunc()

        funConstructors()



        codeFile.writeToDisk()
    }


}







private fun VecClassFileGen.imports() {
    codeFile.writeStr("import java.util.*\n")
    codeFile.writeStr("import kotlin.math.*\n")
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


private fun VecClassFileGen.getOperator() {

    for (t in NUM_TYPES) {
        val convStr = getNumConvData(t.name, "Int").convStr
        vecClassRepr.addElem(
            MethodRepr(
                listOf("operator"),
                "get",
                listOf(FunParam("idx", t.name)),
                true,
            ).also {
                it.writeStr("when (idx$convStr) { ${compNames.withIndex().joinToString("; ") { (compIdx, compName) ->
                    if (compIdx != (compNames.size - 1)) {
                        "$compIdx -> $compName"
                    } else {
                        "else -> $compName"
                    }
                }} }")
            }
        )
    }

}


private fun VecClassFileGen.companionFuncs() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

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

    // FromArray
    if (!typeData.isUnsigned()) {
        val arrayName = "${compType.replaceFirstChar { it.uppercase(LOCALE) }}Array"
        companionRepr.addElem(
            MethodRepr(
                listOf(), "fromArray", listOf(FunParam("array", arrayName)), false, className,
            ).also {
                it.writeContextAwareStr("""
                    require(array.size == $dims) { 
                        "Inputted array isn't of the same dimensions as the vector we're trying to create. Expected $dims, got ${'$'}{array.size}."
                    }
                    return $className(${compNames.withIndex().joinToString { (compIdx, _) -> "array[$compIdx]" }})
                """.trimIndent())

            }
        )
    }

}


private fun VecClassFileGen.casts() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    for (vecToGen in VECS_TO_GEN) {
        if (className == vecToGen.className) continue
        if (dims != vecToGen.dims) continue

        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                "to${vecToGen.className}",
                listOf(),
                true,
            ).also {
                it.writeStr("${vecToGen.className}(${compNames.joinToString(", ") {
                    applyMemberFunc("$it", getNumConvData(compType, vecToGen.compType).convStr, false)
                }})")
            }
        )

    }
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
        "$className(${numStrToTyped("0", compType)})",
        className
    )

    constant(
        "ONE",
        "$className(${numStrToTyped("1", compType)})",
        className
    )

    // Positive infinity and Negative infinity
    if (typeData.isFloatNum()) {
        for ((polarity, varName) in listOf(Pair("positive", "INF"), Pair("negative", "NEG_INF"))) {
            constant(
                varName,
                "$className(${compType}.${polarity.uppercase(LOCALE)}_INFINITY)",
                className
            )
        }
    }

    constant(
        "MIN",
        "$className($compType.MIN_VALUE)",
        className
    )

    constant(
        "MAX",
        "$className($compType.MAX_VALUE)",
        className
    )


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

    // Eq
    vecClassRepr.addElem(
        MethodRepr(listOf(), "eq", listOf(FunParam("other", className)), true)
            .also { it.addElem { code, context ->
                    code.append("${compNames.joinToString(" && ") { "$it == other.$it" }}")
                }
            }
    )

    // Abs
    if (!typeData.isUnsigned()) {
        if (typeData.needToBumpToInt()) {
            val convStr = getNumConvData(typeData.name, "Int").convStr
            val invConvStr = getNumConvData("Int", typeData.name).convStr
            vecClassRepr.addElem(
                MethodRepr(listOf(), "abs", listOf(), true).also {
                    it.writeStr("$className(${compNames.joinToString(", ") { "abs($it$convStr)$invConvStr" }})")
                }
            )
        } else {
            vecClassRepr.addElem(
                MethodRepr(listOf(), "abs", listOf(), true).also {
                    it.writeStr("$className(${compNames.joinToString(", ") { "abs($it)" }})")
                }
            )
        }
    }

    // Mod
    vecClassRepr.addElem(
        MethodRepr(listOf(), "mod", listOf(FunParam("value", compType)), true).also {
            it.writeStr("$className(${compNames.joinToString(", ") { "$it.mod(value)" }})")
        }
    )
    vecClassRepr.addElem(
        MethodRepr(listOf(), "mod", listOf(FunParam("other", className)), true).also {
            it.writeStr("$className(${compNames.joinToString(", ") { "$it.mod(other.$it)" }})")
        }
    )


    // Min, Max
    for (funcName in listOf("min", "max")) {
        val convStr = getNumConvData(typeData.name, if (typeData.isUnsigned()) "UInt" else "Int").convStr
        val invConvStr = getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", typeData.name).convStr
        val applyConvs = typeData.needToBumpToInt()

        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                funcName,
                listOf(FunParam("other", className)),
                true,
            ).also {
                it.writeStr("$className(${compNames.joinToString(", ") {
                    maybeApplyMemberFunc("$funcName(${
                        maybeApplyMemberFunc(it, convStr, false, applyConvs)
                    }, other.${
                        maybeApplyMemberFunc(it, convStr, false, applyConvs)
                    })", invConvStr, false, applyConvs)
                }})")
            }
        )
    }

    // Clamp
    run {
        val convStr = getNumConvData(typeData.name, if (typeData.isUnsigned()) "UInt" else "Int").convStr
        val invConvStr = getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", typeData.name).convStr
        val applyConvs = typeData.needToBumpToInt()

        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                "clamp",
                listOf(FunParam("low", className), FunParam("high", className)),
                true,
            ).also {
                it.writeStr("$className(${compNames.joinToString(", ") {
                    maybeApplyMemberFunc("max(min(${
                        maybeApplyMemberFunc(it, convStr, false, applyConvs)
                    }, high.${
                        maybeApplyMemberFunc(it, convStr, false, applyConvs)
                    }), low.${
                        maybeApplyMemberFunc(it, convStr, false, applyConvs)
                    })", invConvStr, false, applyConvs)
                }})")
            }
        )
    }

    // Dot
    run {
        val startType = if (typeData.isUnsigned()) "UInt" else "Int"
        val convStr = getNumConvData(startType, compType).convStr

        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                "dot",
                listOf(FunParam("other", className)),
                true,
            ).also {
                it.writeStr(maybeApplyMemberFunc("${compNames.joinToString(" + ") {
                    "$it * other.$it"
                }}", convStr, /*true*/false, /*typeData.needToBumpToInt()*/false))
            }
        )
    }

    // Length Sqrd
    for (alias in listOf("lenSq", "magSq")) {
        vecClassRepr.addElem(MethodRepr(listOf(), alias, listOf(), true).also { it.writeStr("dot(this)") })
    }

    // Len
    for (alias in listOf("len", "mag")) {
        // TODO: Prone to overflows, figure out the better way
        val convertStr = if (typeData.isFloatNum()) ""
        else if (typeData.bits < 32) "toFloat()"
        else "toDouble()"

        vecClassRepr.addElem(
            MethodRepr(
                listOf(), alias, listOf(), true
            ).also {
                if (convertStr != "") {
                    it.writeStr("sqrt(lenSq().$convertStr)")
                } else {
                    it.writeStr("sqrt(lenSq())")
                }
            }
        )
    }

    // Dist
    for ((mainFuncName, lenFuncName) in listOf(Pair("dist", "len"), Pair("distSq", "lenSq"))) {
        // Here we convert the vectors instead of converting the result of the subtraction to keep the
        // risk of overflowing at a minimum
        val convertStr = if (typeData.isFloatNum()) ""
        else if (typeData.bits < 32) ".toVec$dims()"
        else ".toDVec$dims()"

        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                mainFuncName,
                listOf(FunParam("other", className)),
                true,
            ).also {
                it.writeStr("(this${convertStr} - other${convertStr}).$lenFuncName()")
            }
        )
    }

    // Normalize
    run {
        val convertStr = if (typeData.isFloatNum()) ""
        else if (typeData.bits < 32) ".toVec$dims()"
        else ".toDVec$dims()"

        for (alias in listOf("normalize", "norm", "unit", "dir")) {
            vecClassRepr.addElem(
                MethodRepr(
                    listOf(),
                    alias,
                    listOf(),
                    true,
                ).also {
                    if (convertStr != "") {
                        it.writeStr("${convertStr.slice(1 until convertStr.length)} / len()")
                    } else {
                        it.writeStr("this / len()")
                    }
                }
            )
        }
    }

    // ElementSum
    for (alias in listOf("elementSum", "eSum")) {
        vecClassRepr.addElem(
            MethodRepr(listOf(), alias, listOf(), true).also {
                it.writeStr("${compNames.joinToString(" + ")}")
            }
        )
    }

    // ElementProd
    for (alias in listOf("elementProd", "eProd")) {
        vecClassRepr.addElem(
            MethodRepr(listOf(), alias, listOf(), true).also {
                it.writeStr("${compNames.joinToString(" * ")}")
            }
        )
    }

    // Min, Max element
    for (funcName in listOf("min", "max")) {
        for (alias in listOf("${funcName}Element", "e${funcName.replaceFirstChar { it.uppercase(LOCALE) } }")) {
            val convStr = getNumConvData(typeData.name, if (typeData.isUnsigned()) "UInt" else "Int").convStr
            val invConvStr = getNumConvData(if (typeData.isUnsigned()) "UInt" else "Int", typeData.name).convStr
            val applyConvs = typeData.needToBumpToInt()

            val args = compNames.map { maybeApplyMemberFunc(it, convStr, false, applyConvs) }

            vecClassRepr.addElem(
                MethodRepr(listOf(), alias, listOf(), true).also {
                    it.writeStr("${maybeApplyMemberFunc(
                        combineArgsWithCommutativeBiFunc(args, funcName),
                        invConvStr, false, applyConvs
                    )}")
                }
            )
        }
    }

    // IndexOfMin, IndexOfMax
    for (m in listOf("min", "max")) {
        val funcName = "indexOf${m.replaceFirstChar { it.uppercase(LOCALE) }}"

        vecClassRepr.addElem(
            MethodRepr(
                listOf(), funcName, listOf(), true,
            ).also {
                it.writeStr("when (e${m.replaceFirstChar { it.uppercase(LOCALE) }}()) { ${compNames.withIndex().joinToString("; ") { (compIdx, compName) ->
                    if (compIdx != (compNames.size - 1)) {
                        "$compName -> $compIdx"
                    } else {
                        "else -> $compIdx"
                    }
                }} }")
            }
        )
    }

    // Manhattan
    vecClassRepr.addElem(MethodRepr(listOf(), "manhattan", listOf(FunParam("other", className)), true)
        .also {
            if (typeData.isUnsigned()) {
                it.writeStr("(this.toIVec$dims() - other.toIVec$dims()).eSum()")
            } else {
                it.writeStr("(this - other).abs().eSum()")
            }
        }
    )

    // Cross
    if (dims == 2 || dims == 3) {
        vecClassRepr.addElem(
            MethodRepr(
                listOf(), "cross", listOf(FunParam("other", className)), true
            ).also {
                val convStr = if (typeData.isUnsigned()) {
                    when (typeData.name) {
                        "UByte" -> ".toInt()"
                        "UShort" -> ".toInt()"
                        "UInt" -> ""
                        "ULong" -> ".toLong()"
                        else -> error("Unsupported unsigned type ${typeData.name}")
                    }
                } else {
                    ""
                }

                if (dims == 2) {
                    val zeroStr = numStrToTyped("0", compType)
                    it.writeStr("${vecName}3($zeroStr, $zeroStr, x$convStr * other.y$convStr - other.x$convStr * y$convStr)")
                }

                if (dims == 3) {
                    val x = "x$convStr"
                    val y = "y$convStr"
                    val z = "z$convStr"
                    val ox = "other.x$convStr"
                    val oy = "other.y$convStr"
                    val oz = "other.z$convStr"
                    it.writeStr("$className($y * $oz - $oy * $z, $z * $ox - $oz * $x, $x * $oy - $ox * $y)")
                }

            }
        )
    }

    // ToArray
    if (!typeData.isUnsigned()) {
        vecClassRepr.addElem(
            MethodRepr(
                listOf(), "toArray", listOf(), true
            ).also {
                val arrayFuncName = "${compType.lowercase(LOCALE)}ArrayOf"
                it.writeStr("$arrayFuncName(${compNames.joinToString()})")
            }
        )
    }


    if (!typeData.isFloatNum()) {
        specialIntegerFuncs()
    }

    if (typeData.isFloatNum()) {
        specialFloatFuncs()
    }
    vecClassRepr.writeStr("\n")


    // Extend
    run {
        val higherDimVecs = getVecsToGenWithDimsAndName(vecName, dims + 1)
        if (higherDimVecs.isEmpty()) return@run
        if (higherDimVecs.size > 1) error("Extend method encountered multiple result vecs, but there should only be one.")
        val higherDimVec = higherDimVecs[0]

        for (t in NUM_TYPES) {
            val convStr = getNumConvData(t.name, compType).convStr

            vecClassRepr.addElem(
                MethodRepr(
                    listOf(),
                    "extend",
                    listOf(FunParam(higherDimVec.compNames.last(), t.name)),
                    true,
                ).also {
                    it.writeStr("${higherDimVec.className}(${
                        higherDimVec.compNames.withIndex().joinToString() { (compIdx, compName) ->
                            if (compIdx == (higherDimVec.dims - 1)) {
                                "$compName$convStr"
                            } else {
                                compName
                            }
                        }
                    })")
                }
            )
        }
    }

    // WithElement
    for (t in NUM_TYPES) {
        vecClassRepr.addElem(
            MethodRepr(
                listOf(),
                "withElement",
                listOf(FunParam("elementIdx", "Int"), FunParam("value", t.name)),
                true,
            ).also {
                it.writeStr("when (elementIdx) { ${compNames.withIndex().joinToString("; ") { (compIdx, compName) ->
                    if (compIdx != (compNames.size - 1)) {
                        "$compIdx -> with${compName.uppercase(LOCALE)}(value)"
                    } else {
                        "else -> with${compName.uppercase(LOCALE)}(value)"
                    }
                }} }")
            }
        )
    }


    // WithXYZW..
    for ((compIndex, compName) in compNames.withIndex()) {
        for (t in NUM_TYPES) {
            val convStr = getNumConvData(t.name, compType).convStr
            vecClassRepr.addElem(
                MethodRepr(
                    listOf(),
                    "with${compName.uppercase(LOCALE)}",
                    listOf(FunParam("value", t.name)),
                    true,
                ).also {
                    it.writeStr("$className(${compNames.withIndex().joinToString(", ") {
                        "${if (compIndex == it.index) "value$convStr" else it.value}"
                    }})")
                }
            )
        }
    }
}

private fun VecClassFileGen.specialIntegerFuncs() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    // FloorDiv
    vecClassRepr.addElem(MethodRepr(
        listOf(), "floorDiv", listOf(FunParam("other", className)), true
    ).also {
        it.writeStr("$className(${compNames.joinToString(", ") { "$it.floorDiv(other.$it)" }})")
    })
    vecClassRepr.addElem(MethodRepr(
        listOf(), "floorDiv", listOf(FunParam("other", compType)), true
    ).also {
        it.writeStr("$className(${compNames.joinToString(", ") { "$it.floorDiv(other)" }})")
    })

    // Binary bitwise ops
    vecClassRepr.writeStr("\n")
    for (op in listOf("and", "or", "xor", "shl", "shr", "ushr")) {
        if (op == "ushr" && typeData.isUnsigned()) continue

        var rightConvStr = ""
        val (convStr, invConvStr) = if (op in listOf("and", "or", "xor") && typeData.bits < 32 && !typeData.isUnsigned()) {
            Pair(getNumConvData(compType, "Int").convStr, getNumConvData("Int", compType).convStr)
        } else if (op in listOf("shl", "shr", "ushr")) {
            rightConvStr = getNumConvData(compType, "Int").convStr
            Pair(
                if (typeData.bits < 32) {
                    getNumConvData(compType, "Int").convStr
                } else {
                    ""
                },
                ""
            )

        } else {
            Pair("", "")
        }

        vecClassRepr.addElem(MethodRepr(
            listOf("infix"), op, listOf(FunParam("other", className)), true
        ).also {
            it.writeStr("$className(${compNames.joinToString(", ") { 
                if ((op in listOf("and", "or", "xor"))) {
                    "$it$convStr $op other.$it$convStr" 
                } else {
                    "$it$convStr $op other.$it$rightConvStr"
                }
            }})")
        })

        vecClassRepr.addElem(MethodRepr(
            listOf("infix"), op, listOf(FunParam("other", compType)), true
        ).also {
            it.writeStr("$className(${compNames.joinToString(", ") {
                if ((op in listOf("and", "or", "xor"))) {
                    "$it$convStr $op other$convStr"
                } else {
                    "$it$convStr $op other$rightConvStr"
                }
            }})")
        })
    }

    // Inv
    run {
        vecClassRepr.addElem(MethodRepr(
            listOf(), "inv", listOf(), true
        ).also {
            val (convStr, invConvStr) = if (!typeData.isUnsigned() && typeData.bits < 32) {
                Pair(getNumConvData(compType, "Int").convStr, getNumConvData("Int", compType).convStr)
            } else {
                Pair("", "")
            }
            it.writeStr("$className(${compNames.joinToString(", ") {
                    "$it$convStr.inv()"
            }})")
        })
    }
}

private fun VecClassFileGen.specialFloatFuncs() {
    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    // Floor
    vecClassRepr.addElem(MethodRepr(
        listOf(), "floor", listOf(), true
    ).also {
        it.writeStr("$className(${compNames.joinToString(", ") { "floor($it)" }})")
    })

    // Ceil
    vecClassRepr.addElem(MethodRepr(
        listOf(), "ceil", listOf(), true
    ).also {
        it.writeStr("$className(${compNames.joinToString(", ") { "ceil($it)" }})")
    })

    // Round
    vecClassRepr.addElem(MethodRepr(
        listOf(), "round", listOf(), true
    ).also {
        it.writeStr("$className(${compNames.joinToString(", ") { "round($it)" }})")
    })

    // Fract
    vecClassRepr.addElem(MethodRepr(
        listOf(), "fract", listOf(), true
    ).also {
        it.writeStr("mod(${numStrToTyped("1", compType)})")
    })

    // Quat mul (DQuat coming later?)
    vecClassRepr.addElem(MethodRepr(
        listOf(), "quatMul", listOf(FunParam("q", "Quat")), false, className,
    ).also {
        it.writeContextAwareStr("""
            val u = $className(q.x, q.y, q.z)
            val scalar = q.w${if(compType=="Double")".toDouble()" else ""}
            return (
                    (2f * u.dot(this) * u) +
                    (scalar * scalar - u.dot(u)) * this +
                    (2f * scalar * u.cross(this))
            )""".trimIndent())
    })

}


private fun VecClassFileGen.toStringFunc() {

    val typeData = NAMES_TO_NUM_TYPES[compType]!!

    vecClassRepr.addElem(MethodRepr(listOf("override"), "toString", listOf(), false, "String")
        .also {
            var s = "return (\"$className(\" + \n"
            compNames.withIndex().forEach { (compIdx, compName) ->
                s += "        "
                if (typeData.isFloatNum()) {
                    s += "\"$compName=\${\"%.5f\".format(Locale.ENGLISH, $compName)}"
                } else {
                    s += "\"$compName=\$$compName"
                }

                if (compIdx != compNames.size - 1) s += ", \" + \n"
                else s += ")\")"
            }
            it.writeContextAwareStr(s)
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
    val extraConversionNeeded = typeData.needToBumpToInt() && (opName in
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
