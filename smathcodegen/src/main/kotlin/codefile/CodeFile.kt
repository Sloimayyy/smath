package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.smathGenFolderPath
import java.nio.file.Path

class CodeFile(
    val fileName: String,
    val packageStr: String,

    folderRelativeLoc: String,
) : CodeWriteElement, CwElementContainer {
    val folderPath: Path = smathGenFolderPath().resolve(folderRelativeLoc)
    val filePath: Path = folderPath.resolve(fileName)

    val els = mutableListOf<CodeWriteElement>()

    override fun addElem(el: CodeWriteElement) = els.add(el)


    fun writeToDisk() {
        folderPath.toFile().mkdirs()

        val code = StringBuilder("package $packageStr\n\n")
        val writerContext = CodeWriterContext(0)
        this.write(code, writerContext)

        filePath.toFile().writeText(code.toString())
        println("writing ${filePath}")
    }

    override fun write(code: StringBuilder, context: CodeWriterContext) {
        for (cwe in els) cwe.write(code, context)
    }
}