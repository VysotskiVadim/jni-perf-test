package dev.vadzimv.jniperftest

import androidx.annotation.Keep

class NativeDataProvider {
    companion object {
        init {
            System.loadLibrary("jniperftest")
        }

        @[JvmStatic Keep]
        external fun initStringArray(size: Int)

        @[JvmStatic Keep]
        external fun getNativeStringFromArray(index: Int): String


        @[JvmStatic Keep]
        external fun initIntArray(size: Int)

        @[JvmStatic Keep]
        external fun getIntFromArray(index: Int): Int
    }
}