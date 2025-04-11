package com.sloimay.smathcodegen.codefile

fun interface CodeWriteElement {

    companion object

    fun write(code: StringBuilder, context: CodeWriterContext)

}

