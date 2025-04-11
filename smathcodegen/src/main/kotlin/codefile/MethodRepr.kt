package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.codefile.params.FunParam
import com.sloimay.smathcodegen.indentStr

class MethodRepr(
    val qualifiers: List<String>,
    val name: String,
    val params: List<FunParam>,
    val oneLiner: Boolean = false,
    val returnType: String? = null,
) : CodeWriteElement, CwElementContainer {

    private val els = mutableListOf<CodeWriteElement>()

    override fun addElem(el: CodeWriteElement) = els.add(el)

    override fun write(code: StringBuilder, context: CodeWriterContext) {
        code.append(indentStr(context.indent))

        code.append(qualifiers.joinToString("") { "$it " })
        code.append("fun $name(")
        code.append(params.joinToString(", ") { it.str() })
        code.append(")")
        if (returnType != null) {
            code.append(": $returnType")
        }

        if (oneLiner) {
            code.append(" = ")
        } else {
            code.append(" {\n")
        }

        context.indent += 4
        els.forEach { it.write(code, context) }
        context.indent -= 4
        code.append("\n")

        if (!oneLiner) {
            code.append(indentStr(context.indent))
            code.append("}\n")
        }
    }

}