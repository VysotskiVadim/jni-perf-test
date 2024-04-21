package dev.vadzimv.jniperftest

import androidx.annotation.Keep

class NativeDataProvider {
    companion object {
        init {
            System.loadLibrary("jniperftest")
        }

        @[JvmStatic Keep]
        external fun provideNativeString(): String
    }
}