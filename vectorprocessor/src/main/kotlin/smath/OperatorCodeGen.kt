package com.sloimay.smath

import kotlin.math.roundToLong


fun genOperators(genContext: GenContext) {

    val (
        code,
        packageName,
        className,
        compType,
        dims,
        compNames
    ) = genContext

    code.append("// Operators\n")
    genBinaryOp(genContext, "plus", "+")
    genBinaryOp(genContext, "minus", "-")
    genBinaryOp(genContext, "times", "*")
    genBinaryOp(genContext, "div", "/")
    genBinaryOp(genContext, "rem", "%")

    if (compType !in UNUM_TYPES) {
        genUnaryOp(genContext, "unaryMinus", "-")
    }
    genUnaryOp(genContext, "unaryPlus", "+")

}









private fun genUnaryOp(genContext: GenContext, opName: String, opSymbol: String) {
    val (
        code,
        packageName,
        className,
        compType,
        dims,
        compNames
    ) = genContext

    code
        .append("operator fun $className.$opName(): $className = $className(")
        .append(compNames.joinToString(", ") { "${opSymbol}$it" })
        .append(")")
    code.append("\n")
}




private fun genBinaryOp(genContext: GenContext, opName: String, opSymbol: String) {
    val (
        code,
        packageName,
        className,
        compType,
        dims,
        compNames
    ) = genContext



    code
        .append("operator fun $className.$opName(other: $className): $className = $className(")
        .append(compNames.joinToString(", ") { "$it $opSymbol other.$it" })
        .append(")")

    code.append("\n")

    code
        .append("operator fun $className.$opName(other: $compType): $className = $className(")
        .append(compNames.joinToString(", ") { "$it $opSymbol other" })
        .append(")")

    code.append("\n")
}