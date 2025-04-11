package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.indentStr

class ValDeclarationRepr(
    val qualifiers: List<String>,
    val mutable: Boolean,
    val name: String,
    val type: String,
    val normalizedExpr: String? = null,
) : CodeWriteElement {

    private val els = mutableListOf<CodeWriteElement>()

    fun addElem(el: CodeWriteElement) = els.add(el)

    override fun write(code: StringBuilder, context: CodeWriterContext) {
        val indentStr = indentStr(context.indent)

        code.append(indentStr)
        code.append(qualifiers.joinToString("") { "$it " })
        code.append("${if (mutable) "var" else "val"} $name: $type")

        if (normalizedExpr != null) {
            code.append(" = ")
            context.indent += 4
            val exprIndentStr = indentStr(context.indent)
            code.append(normalizedExpr.replace("\n", "\n$exprIndentStr"))
            context.indent -= 4
        }

        code.append("\n")
    }
}