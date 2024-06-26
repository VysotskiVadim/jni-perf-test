package dev.vadzimv.jniperftest.benchmark

import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vadzimv.jniperftest.NativeDataProvider
import dev.vadzimv.jniperftest.PlatformDataProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TEST_ARRAY_SIZE = 100_000

@RunWith(AndroidJUnit4::class)
class IntArrayBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()


    @Test
    fun measurePlatformIntArrayAccess() {
        PlatformDataProvider.initIntArray(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                PlatformDataProvider.getIntFromArray(i)
            }
        }
    }

    @Test
    fun measureNativeIntArrayAccess() {
        NativeDataProvider.initIntArray(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getIntFromArray(i)
            }
        }
    }

    @Test
    fun measureFastNativeIntArrayAccess() {
        NativeDataProvider.initIntArray(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getIntFromArrayFastNative(i)
            }
        }
    }

    @Test
    fun measureCriticalNativeIntArrayAccess() {
        NativeDataProvider.initIntArray(TEST_ARRAY_SIZE)
        benchmarkRule.measureRepeated {
            for (i in 0 until TEST_ARRAY_SIZE) {
                NativeDataProvider.getIntFromArrayCriticalNative(i)
            }
        }
    }
}