package com.sloimay.smathcodegen.codefile.params

class FunParam(
    val name: String,
    val typeName: String,
    val defaultStr: String? = null,
    val qualifiers: List<String>? = null
) {
    fun str(): String {
        var s = ""
        if (qualifiers != null) {
            s += qualifiers.joinToString(separator = " ")
            s += " "
        }
        s += "$name: $typeName"
        if (defaultStr != null) {
            s += " = $defaultStr"
        }
        return s
    }
}