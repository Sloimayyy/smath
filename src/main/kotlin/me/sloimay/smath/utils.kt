package me.sloimay.smath

class Utils {

    companion object {

        fun remap(v: Float, vl: Float, vu: Float, ol: Float, ou: Float): Float {
            return (v - vl) * ((ou - ol) / (vu - vl)) + ol
        }

        fun remap(v: Double, vl: Double, vu: Double, ol: Double, ou: Double): Double {
            return (v - vl) * ((ou - ol) / (vu - vl)) + ol
        }

    }

}