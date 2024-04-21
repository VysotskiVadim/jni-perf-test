package dev.vadzimv.jniperftest

object NativeDataProvider {
    init {
        System.loadLibrary("jniperftest")
    }

    external fun stringFromJNI(): String
}