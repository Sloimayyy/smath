package com.sloimay.smathcodegen.codefile

fun interface CodeWriteElement {

    fun write(code: StringBuilder, context: CodeWriterContext)

}