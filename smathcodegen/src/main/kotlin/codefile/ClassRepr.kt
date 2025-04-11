package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.codefile.params.ClassParam
import com.sloimay.smathcodegen.indentStr

class ClassRepr(
    val qualifiers: List<String>,
    val name: String,
    val params: List<ClassParam>,
) : CodeWriteElement, CwElementContainer {

    private val els = mutableListOf<CodeWriteElement>()

    override fun addElem(el: CodeWriteElement) = els.add(el)

    override fun write(code: StringBuilder, context: CodeWriterContext) {
        code.append(indentStr(context.indent))

        code.append(qualifiers.joinToString("") { "$it " })
        code.append("class $name")
        if (params.isNotEmpty()) {
            code.append("(")
            code.append(params.joinToString(", ") { it.str() })
            code.append(")")
        }
        code.append(" {\n")

        context.indent += 4
        els.forEach { it.write(code, context) }
        context.indent -= 4

        code.append(indentStr(context.indent))
        code.append("\n}\n")
    }
}