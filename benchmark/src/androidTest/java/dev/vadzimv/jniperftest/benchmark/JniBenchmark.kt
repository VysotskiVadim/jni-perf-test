package dev.vadzimv.jniperftest.benchmark

import android.util.Log
import androidx.benchmark.junit4.BenchmarkRule
import androidx.benchmark.junit4.measureRepeated
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.vadzimv.jniperftest.NativeDataProvider
import dev.vadzimv.jniperftest.PlatformDataProvider
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Benchmark, which will execute on an Android device.
 *
 * The body of [BenchmarkRule.measureRepeated] is measured in a loop, and Studio will
 * output the result. Modify your code to see how it affects performance.
 */
@RunWith(AndroidJUnit4::class)
class JniBenchmark {

    @get:Rule
    val benchmarkRule = BenchmarkRule()

    @Test
    fun measurePlatformArrayOfStringsAccess() {
        benchmarkRule.measureRepeated {
            for (i in 0 until 10_000) {
                PlatformDataProvider.getStringFromArray(i)
            }
        }
    }

    @Test
    fun measureNativeArrayOfStringsAccess() {
        benchmarkRule.measureRepeated {
            for (i in 0 until 10_000) {
                NativeDataProvider.getNativeStringFromArray(i)
            }
        }
    }
}