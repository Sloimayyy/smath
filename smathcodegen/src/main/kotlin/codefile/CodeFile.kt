package com.sloimay.smathcodegen.codefile

import com.sloimay.smathcodegen.smathGenFolderPath
import java.nio.file.Path

class CodeFile(
    val fileName: String,
    val packageStr: String,

    folderRelativeLoc: String,
) {
    val folderPath: Path = smathGenFolderPath().resolve(folderRelativeLoc)
    val filePath: Path = folderPath.resolve(fileName)

    val code = StringBuilder("package $packageStr\n\n")

    fun writeToDisk() {
        folderPath.toFile().mkdirs()
        filePath.toFile().writeText(code.toString())
    }



    fun oneLinerFunc(
        qualifiers: List<String>,
        funcName: String,
        params: List<FunParam>,
        returnType: String,
        body: String,
    ) {
        code.append("${qualifiers.joinToString(separator = "") { "$it " }}fun $funcName")
        code.append("(")
        code.append(params.joinToString(separator = ", ") { it.str() })
        code.append(")")
        code.append(": $returnType = ")
        code.append(body)
        code.append("\n")
    }


    fun oneLinerExtensionFunc(
        qualifiers: List<String>,
        extendedClassName: String,
        funcName: String,
        params: List<FunParam>,
        returnType: String,
        body: String,
    ) {
        oneLinerFunc(
            qualifiers,
            "$extendedClassName.$funcName",
            params,
            returnType,
            body,
        )
    }


    fun func(
        qualifiers: List<String>,
        funcName: String,
        params: List<FunParam>,
        returnType: String,
        body: String,
    ) {
        code.append("${qualifiers.joinToString(separator = " ")} fun $funcName")
        code.append("(")
        code.append(params.joinToString(separator = ", ") { it.str() })
        code.append(")")
        code.append(": $returnType {\n")
        code.append(body)
        code.append("\n}\n")
    }

    fun extensionFunc(
        qualifiers: List<String>,
        extendedClassName: String,
        funcName: String,
        params: List<FunParam>,
        returnType: String,
        body: String,
    ) {
        func(
            qualifiers,
            "$extendedClassName.$funcName",
            params,
            returnType,
            body,
        )
    }

    fun variableDeclaration(
        qualifiers: List<String>,
        mutability: String,
        varName: String,
        varType: String?,
        expr: String,
    ) {
        if (qualifiers.isNotEmpty()) {
            code.append("${qualifiers.joinToString(separator = " ")} ")
        }
        code.append("$mutability $varName")
        if (varType != null) {
            code.append(": $varType")
        }
        code.append(" = $expr\n")
    }


}