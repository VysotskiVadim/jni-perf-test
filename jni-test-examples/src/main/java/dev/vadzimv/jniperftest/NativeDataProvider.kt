package dev.vadzimv.jniperftest

import androidx.annotation.Keep
import dalvik.annotation.optimization.CriticalNative
import dalvik.annotation.optimization.FastNative
import java.nio.ByteBuffer

class NativeDataProvider {
    companion object {
        init {
            System.loadLibrary("jniperftest")
        }

        @[JvmStatic Keep]
        external fun initStringArray(size: Int)

        @[JvmStatic Keep]
        external fun getNativeStringFromArray(index: Int): String

        @[JvmStatic Keep FastNative]
        external fun getFastNativeStringFromArray(index: Int): String


        @[JvmStatic Keep]
        external fun initIntArray(size: Int)

        @[JvmStatic Keep]
        external fun getIntFromArray(index: Int): Int

        @[JvmStatic Keep FastNative]
        external fun getIntFromArrayFastNative(index: Int): Int

        @[JvmStatic Keep CriticalNative]
        external fun getIntFromArrayCriticalNative(index: Int): Int


        @[JvmStatic Keep]
        external fun initDoubleArray(size: Int)

        @[JvmStatic Keep]
        external fun getDoubleFromArray(index: Int): Double

        @[JvmStatic Keep FastNative]
        external fun getDoubleFromArrayFastNative(index: Int): Double

        @[JvmStatic Keep CriticalNative]
        external fun getDoubleFromArrayCriticalNative(index: Int): Double

        @[JvmStatic Keep]
        external fun initByteBuffer(size: Int)

        @[JvmStatic Keep]
        external fun getByteBuffer(): ByteBuffer
    }
}