package dev.vadzimv.jniperftest.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vadzimv.jniperftest.NativeDataProvider
import dev.vadzimv.jniperftest.PlatformDataProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_ARRAY_SIZE = 1_000_000

@RunWith(AndroidJUnit4::class)
class ByteBufferBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun measureByteAccessViaByteBuffer() {
        NativeDataProvider.initByteBuffer(TEST_ARRAY_SIZE)
        val byteBuffer = NativeDataProvider.getByteBuffer()
        byteBuffer.get(4)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                byteBuffer.get(i)
            }
        }
    }

    @Test
    fun measureByteAccessDirectlyBuffer() {
        NativeDataProvider.initByteBuffer(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getByteFromByteBuffer(i)
            }
        }
    }

    @Test
    fun measureByteAccessDirectlyBufferFastNative() {
        NativeDataProvider.initByteBuffer(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getByteFromByteBufferFastNative(i)
            }
        }
    }

    @Test
    fun measureByteAccessDirectlyBufferCriticalNative() {
        NativeDataProvider.initByteBuffer(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getByteFromByteBufferCriticalNative(i)
            }
        }
    }

    @Test
    fun measureByteAccessFromPlatformArray() {
        PlatformDataProvider.initByteArray(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                PlatformDataProvider.getFromByteArray(i)
            }
        }
    }
}