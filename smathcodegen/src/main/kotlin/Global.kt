package com.sloimay.smathcodegen

val COORDS_NAME = listOf(
    "x", "y", "z", "w", "v", "u", "t", "s"
)

class VecToGen(val vecName: String, val compType: String, val dims: Int) {
    val className = vecName + dims.toString()
    val compNames = COORDS_NAME.toList().slice(0 until dims)
}



val VECS_TO_GEN = (cartesianProd(listOf(
    listOf(
        Pair("BVec", "Byte"),
        Pair("SVec", "Short"),
        Pair("IVec", "Int"),
        Pair("LVec", "Long"),
        Pair("Vec", "Float"),
        Pair("DVec", "Double"),
        //Pair("UVec", "UInt"),
    ).map { it },
    (2..4).map { it },
))
    .asSequence().map {
        val (vecName, compType) = it[0] as Pair<String, String>
        val dims = it[1] as Int
        VecToGen(vecName, compType, dims)
    }.toList())

/*listOf(



    VecToGen("BVec", "Byte", 2),
    VecToGen("BVec", "Byte", 3),
    VecToGen("BVec", "Byte", 4),

    //VecToGen("UbVec", "UByte", 2),
    //VecToGen("UbVec", "UByte", 3),
    //VecToGen("UbVec", "UByte", 4),

    VecToGen("SVec", "Short", 2),
    VecToGen("SVec", "Short", 3),
    VecToGen("SVec", "Short", 4),

    //VecToGen("UsVec", "UShort", 2),
    //VecToGen("UsVec", "UShort", 3),
    //VecToGen("UsVec", "UShort", 4),

    VecToGen("IVec", "Int", 2),
    VecToGen("IVec", "Int", 3),
    VecToGen("IVec", "Int", 4),

    //VecToGen("UiVec", "UInt", 2),
    //VecToGen("UiVec", "UInt", 3),
    //VecToGen("UiVec", "UInt", 4),

    VecToGen("LVec", "Long", 2),
    VecToGen("LVec", "Long", 3),
    VecToGen("LVec", "Long", 4),

    //VecToGen("UlVec", "ULong", 2),
    //VecToGen("UlVec", "ULong", 3),
    //VecToGen("UlVec", "ULong", 4),

    VecToGen("Vec", "Float", 2),
    VecToGen("Vec", "Float", 3),
    VecToGen("Vec", "Float", 4),

    VecToGen("DVec", "Double", 2),
    VecToGen("DVec", "Double", 3),
    VecToGen("DVec", "Double", 4),
     */

    /*VecToGen("Vec", "Float", 3),
    VecToGen("DVec", "Double", 3),
    VecToGen("UbVec", "UByte", 3),
    VecToGen("BVec", "Byte", 3),
    VecToGen("LVec", "Long", 3),
    VecToGen("SVec", "Short", 4),
)*/

fun getVecsToGenWithDimsAndName(name: String, dims: Int): List<VecToGen> {
    return VECS_TO_GEN.filter { name == it.vecName && dims == it.dims }
}



class NumConvData(
    val from: String,
    val to: String,
    vararg steps: String,
) {
    val convStr = steps.joinToString("") { ".to$it()" }
}

/**
NumConvData("Byte", "Byte", "Byte"),
NumConvData("Byte", "UByte", "UByte"),
NumConvData("Byte", "Short", "Short"),
NumConvData("Byte", "UShort", "UShort"),
NumConvData("Byte", "Int", "Int"),
NumConvData("Byte", "UInt", "UInt"),
NumConvData("Byte", "Long", "Long"),
NumConvData("Byte", "ULong", "ULong"),
NumConvData("Byte", "Float", "Float"),
NumConvData("Byte", "Double", "Double"),
 */

val NUM_CONV_DATA = listOf(
    NumConvData("Byte", "Byte", ),
    NumConvData("Byte", "UByte", "UByte"),
    NumConvData("Byte", "Short", "Short"),
    NumConvData("Byte", "UShort", "UShort"),
    NumConvData("Byte", "Int", "Int"),
    NumConvData("Byte", "UInt", "UInt"),
    NumConvData("Byte", "Long", "Long"),
    NumConvData("Byte", "ULong", "ULong"),
    NumConvData("Byte", "Float", "Float"),
    NumConvData("Byte", "Double", "Double"),

    NumConvData("UByte", "Byte", "Byte"),
    NumConvData("UByte", "UByte", ),
    NumConvData("UByte", "Short", "Short"),
    NumConvData("UByte", "UShort", "UShort"),
    NumConvData("UByte", "Int", "Int"),
    NumConvData("UByte", "UInt", "UInt"),
    NumConvData("UByte", "Long", "Long"),
    NumConvData("UByte", "ULong", "ULong"),
    NumConvData("UByte", "Float", "Float"),
    NumConvData("UByte", "Double", "Double"),

    NumConvData("Short", "Byte", "Byte"),
    NumConvData("Short", "UByte", "UByte"),
    NumConvData("Short", "Short", ),
    NumConvData("Short", "UShort", "UShort"),
    NumConvData("Short", "Int", "Int"),
    NumConvData("Short", "UInt", "UInt"),
    NumConvData("Short", "Long", "Long"),
    NumConvData("Short", "ULong", "ULong"),
    NumConvData("Short", "Float", "Float"),
    NumConvData("Short", "Double", "Double"),

    NumConvData("UShort", "Byte", "Byte"),
    NumConvData("UShort", "UByte", "UByte"),
    NumConvData("UShort", "Short", "Short"),
    NumConvData("UShort", "UShort", ),
    NumConvData("UShort", "Int", "Int"),
    NumConvData("UShort", "UInt", "UInt"),
    NumConvData("UShort", "Long", "Long"),
    NumConvData("UShort", "ULong", "ULong"),
    NumConvData("UShort", "Float", "Float"),
    NumConvData("UShort", "Double", "Double"),

    NumConvData("Int", "Byte", "Byte"),
    NumConvData("Int", "UByte", "UByte"),
    NumConvData("Int", "Short", "Short"),
    NumConvData("Int", "UShort", "UShort"),
    NumConvData("Int", "Int", ),
    NumConvData("Int", "UInt", "UInt"),
    NumConvData("Int", "Long", "Long"),
    NumConvData("Int", "ULong", "ULong"),
    NumConvData("Int", "Float", "Float"),
    NumConvData("Int", "Double", "Double"),

    NumConvData("UInt", "Byte", "Byte"),
    NumConvData("UInt", "UByte", "UByte"),
    NumConvData("UInt", "Short", "Short"),
    NumConvData("UInt", "UShort", "UShort"),
    NumConvData("UInt", "Int", "Int"),
    NumConvData("UInt", "UInt", ),
    NumConvData("UInt", "Long", "Long"),
    NumConvData("UInt", "ULong", "ULong"),
    NumConvData("UInt", "Float", "Float"),
    NumConvData("UInt", "Double", "Double"),

    NumConvData("Long", "Byte", "Byte"),
    NumConvData("Long", "UByte", "UByte"),
    NumConvData("Long", "Short", "Short"),
    NumConvData("Long", "UShort", "UShort"),
    NumConvData("Long", "Int", "Int"),
    NumConvData("Long", "UInt", "UInt"),
    NumConvData("Long", "Long", ),
    NumConvData("Long", "ULong", "ULong"),
    NumConvData("Long", "Float", "Float"),
    NumConvData("Long", "Double", "Double"),

    NumConvData("ULong", "Byte", "Byte"),
    NumConvData("ULong", "UByte", "UByte"),
    NumConvData("ULong", "Short", "Short"),
    NumConvData("ULong", "UShort", "UShort"),
    NumConvData("ULong", "Int", "Int"),
    NumConvData("ULong", "UInt", "UInt"),
    NumConvData("ULong", "Long", "Long"),
    NumConvData("ULong", "ULong", ),
    NumConvData("ULong", "Float", "Float"),
    NumConvData("ULong", "Double", "Double"),

    NumConvData("Float", "Byte", "Int", "Byte"),
    NumConvData("Float", "UByte", "UInt", "UByte"),
    NumConvData("Float", "Short", "Int", "Short"),
    NumConvData("Float", "UShort", "UInt", "UShort"),
    NumConvData("Float", "Int", "Int"),
    NumConvData("Float", "UInt", "UInt"),
    NumConvData("Float", "Long", "Long"),
    NumConvData("Float", "ULong", "ULong"),
    NumConvData("Float", "Float", ),
    NumConvData("Float", "Double", "Double"),

    NumConvData("Double", "Byte", "Int", "Byte"),
    NumConvData("Double", "UByte", "UInt", "UByte"),
    NumConvData("Double", "Short", "Int", "Short"),
    NumConvData("Double", "UShort", "UInt", "UShort"),
    NumConvData("Double", "Int", "Int"),
    NumConvData("Double", "UInt", "UInt"),
    NumConvData("Double", "Long", "Long"),
    NumConvData("Double", "ULong", "ULong"),
    NumConvData("Double", "Float", "Float"),
    NumConvData("Double", "Double", ),
)


class NumTypeData(
    val name: String,
    val clazz: Class<*>,
    val bits: Int,
) {
    fun isFloat(): Boolean = name == "Float" || name == "Double"
    fun isUnsigned(): Boolean = name in listOf("UByte", "UShort", "UInt", "ULong")
    fun needToBumpToInt(): Boolean = name in listOf("Byte", "UByte", "Short", "UShort")
}


val NUM_TYPES = run {
    val l = listOf(
        NumTypeData("Byte", Byte::class.java, 8),
        NumTypeData("UByte", UByte::class.java, 8),
        NumTypeData("Short", Short::class.java, 16),
        NumTypeData("UShort", UShort::class.java, 16),
        NumTypeData("Int", Int::class.java, 32),
        NumTypeData("UInt", UInt::class.java, 32),
        NumTypeData("Long", Long::class.java, 64),
        NumTypeData("ULong", ULong::class.java, 64),

        NumTypeData("Float", Float::class.java, 32),
        NumTypeData("Double", Double::class.java, 64),
    )
    l
}

val JAVA_NUM_TYPES = run {
    val l = listOf(
        NumTypeData("Byte", Byte::class.java, 8),
        NumTypeData("Short", Short::class.java, 16),
        NumTypeData("Int", Int::class.java, 32),
        NumTypeData("Long", Long::class.java, 64),

        NumTypeData("Float", Float::class.java, 32),
        NumTypeData("Double", Double::class.java, 64),
    )
    l
}

val NAMES_TO_NUM_TYPES = run {
    val m = HashMap<String, NumTypeData>()
    m.putAll(NUM_TYPES.map { it.name to it })
    m
}

val UNUM_TYPES = listOf(
    NAMES_TO_NUM_TYPES["UByte"],
    NAMES_TO_NUM_TYPES["UShort"],
    NAMES_TO_NUM_TYPES["UInt"],
    NAMES_TO_NUM_TYPES["ULong"],
)

