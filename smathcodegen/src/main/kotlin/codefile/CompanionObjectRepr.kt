package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.indentStr

class CompanionObjectRepr() : CodeWriteElement, CwElementContainer {

    private val els = mutableListOf<CodeWriteElement>()

    override fun addElem(el: CodeWriteElement) = els.add(el)

    override fun write(code: StringBuilder, context: CodeWriterContext) {
        code.append(indentStr(context.indent))

        code.append("companion object {\n")

        context.indent += 4
        els.forEach { it.write(code, context) }
        context.indent -= 4

        code.append(indentStr(context.indent))
        code.append("}\n")
    }
}