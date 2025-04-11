package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.indentStr

interface CwElementContainer {

    companion object

    fun addElem(el: CodeWriteElement): Boolean

    fun writeStr(body: String) {
        this.addElem { code, context -> code.append(body) }
    }

    fun writeContextAwareStr(body: String) {
        this.addElem { code, context ->
            val indentStr = indentStr(context.indent)
            code.append( indentStr + body.replace("\n", "\n$indentStr") )
        }
    }
}



