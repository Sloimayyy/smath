package com.sloimay.smathcodegen

import java.io.File
import java.nio.file.Paths
import kotlin.math.roundToLong

val SMATH_GENERATED_FOLDER_NAME = "smathgenerated"

fun smathGenFolderPath() = Paths.get(File("").absolutePath).parent.resolve("src/main/kotlin/$SMATH_GENERATED_FOLDER_NAME")


fun emptySmathGenFolder() {
    val folderPath = smathGenFolderPath()
    if (SMATH_GENERATED_FOLDER_NAME !in folderPath.toString()) {
        error(
            "Trying to empty a folder that isn't named smathgenerated. " +
                    "This is a safety measure because I can be dumb lol"
        )
    }

    folderPath.toFile().listFiles()?.forEach {
        it.deleteRecursively()
    }
}


/**
 * Doesn't work for values greater than 2^53
 */
fun numStrToTyped(numStr: String, type: String): String {
    val v = numStr.toDouble()
    val typeData = NAMES_TO_NUM_TYPES[type]!!

    val s = when (type) {
        "Byte" -> "${v.roundToLong()}.toByte()"
        "UByte" -> "${v.roundToLong()}.toUByte()"
        "Short" -> "${v.roundToLong()}.toShort()"
        "UShort" -> "${v.roundToLong()}.toUShort()"
        "Int" -> "${v.roundToLong()}"
        "UInt" -> "${v.roundToLong()}u"
        "Long" -> "${v.roundToLong()}L"
        "ULong" -> "${v.roundToLong()}L.toULong()"
        "Float" -> "${v}f"
        "Double" -> "${v}"
        else -> error("Unsupported type: $type")
    }
    return s
}


fun String.compTypeIsFloat() = this == "Float" || this == "Double"


fun maybeSurroundWithParen(expr: String, parentheses: Boolean = true): String {
    return (if (parentheses) "(" else "") + expr + (if (parentheses) ")" else "")
}

fun maybeApplyMemberFunc(expr: String, application: String, parentheses: Boolean = true, apply: Boolean = true): String {
    return maybeSurroundWithParen(expr, parentheses) + if (apply) application else ""
}

fun applyMemberFunc(expr: String, application: String, parentheses: Boolean = true): String {
    return maybeSurroundWithParen(expr, parentheses) + application
}

fun getNumConvData(from: String, to: String): NumConvData {
    for (numConvData in NUM_CONV_DATA) {
        if (numConvData.from == from && numConvData.to == to) {
            return numConvData
        }
    }
    error("Unrecognized num conversion: ${from} -> ${to}")
}

fun indentStr(indent: Int): String {
    return " ".repeat(indent)
}

fun <T> cartesianProd(inputSpaces: List<List<T>>): Iterator<List<T>> {
    return object : Iterator<List<T>> {

        val indexes = inputSpaces.map { 0 }.toMutableList().also { it[it.size - 1] = -1 }
        val limits = inputSpaces.map { it.size }

        override fun hasNext(): Boolean {
            return !indexes.withIndex().all { (idx, v) -> v == limits[idx] - 1 }
        }

        override fun next(): List<T> {

            // Iterate indexes
            for (i in (indexes.size-1) downTo 0) {
                indexes[i] += 1
                if (indexes[i] >= limits[i]) {
                    indexes[i] = 0
                } else {
                    break
                }
            }

            return indexes.mapIndexed { inputSpaceIdx, idxInInputSpace -> inputSpaces[inputSpaceIdx][idxInInputSpace] }
        }

    }
}

fun highestSetBit(i: Int): Int {
    return i.takeHighestOneBit().countTrailingZeroBits()
}

fun combineArgsWithCommutativeBiFunc(args: List<String>, biFuncName: String): String {
    val argCount = args.size
    require(argCount >= 2) { "Requires arg count to be greater or equal to 2 but got $argCount" }

    val startLayer = highestSetBit(argCount - 1)
    val layerIdxOffset = 1 shl startLayer

    fun recur(argIdx: Int, layer: Int, layerIdxOffset: Int): String {
        if (layer == 0) {
            return args[argIdx]
        }

        val left = recur(argIdx, layer - 1, layerIdxOffset / 2)

        val rightIdx = argIdx + layerIdxOffset
        val right = if (rightIdx < args.size) {
            recur(rightIdx, layer - 1, layerIdxOffset / 2)
        } else {
            null
        }

        if (right != null) {
            return "$biFuncName($left, $right)"
        } else {
            return left
        }
    }


    return recur(0, startLayer + 1, layerIdxOffset)
}

fun combineArgsWithCommutativeBiInfix(args: List<String>, infixStr: String): String {
    val argCount = args.size
    require(argCount >= 2) { "Requires arg count to be greater or equal to 2 but got $argCount" }

    val startLayer = highestSetBit(argCount - 1)
    val layerIdxOffset = 1 shl startLayer

    fun recur(argIdx: Int, layer: Int, layerIdxOffset: Int): String {
        if (layer == 0) {
            return args[argIdx]
        }

        val left = recur(argIdx, layer - 1, layerIdxOffset / 2)

        val rightIdx = argIdx + layerIdxOffset
        val right = if (rightIdx < args.size) {
            recur(rightIdx, layer - 1, layerIdxOffset / 2)
        } else {
            null
        }

        if (right != null) {
            return "($left $infixStr $right)"
        } else {
            return left
        }
    }


    return recur(0, startLayer + 1, layerIdxOffset)
}

