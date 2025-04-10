package com.sloimay.smath

data class GenContext(
    val code: StringBuilder,
    val packageName: String,
    val className: String,
    val compType: String,
    val dims: Int,
    val compNames: List<String>
)