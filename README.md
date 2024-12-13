Benchmarks run on Pixel 6:

```logs
6,053,799   ns           0 allocs    trace    ByteBufferBenchmark.measureByteAccessDirectlyBufferFastNative
4,816,597   ns           0 allocs    trace    ByteBufferBenchmark.measureByteAccessViaByteBuffer
19,041,202   ns           0 allocs    trace    ByteBufferBenchmark.measureByteAccessDirectlyBuffer
1,830,037   ns           0 allocs    trace    ByteBufferBenchmark.measureByteAccessDirectlyBufferCriticalNative
1,789,507   ns           0 allocs    trace    ByteBufferBenchmark.measureByteAccessFromPlatformArray
178,999   ns           0 allocs    trace    DoubleArrayBenchmark.measurePlatformDoubleArrayAccess
631,636   ns           0 allocs    trace    DoubleArrayBenchmark.measureFastNativeDoubleArrayAccess
185,739   ns           0 allocs    trace    DoubleArrayBenchmark.measureCriticalNativeDoubleArrayAccess
1,905,186   ns           0 allocs    trace    DoubleArrayBenchmark.measureNativeDoubleArrayAccess
1,907,372   ns           0 allocs    trace    IntArrayBenchmark.measureNativeIntArrayAccess
178,951   ns           0 allocs    trace    IntArrayBenchmark.measurePlatformIntArrayAccess
595,690   ns           0 allocs    trace    IntArrayBenchmark.measureFastNativeIntArrayAccess
181,663   ns           0 allocs    trace    IntArrayBenchmark.measureCriticalNativeIntArrayAccess
178,986   ns           0 allocs    trace    StringArrayBenchmark.measurePlatformArrayOfStringsAccess
5,618,029   ns      100000 allocs    trace    StringArrayBenchmark.measureFastNativeArrayOfStringsAccess
7,800,811   ns      100000 allocs    trace    StringArrayBenchmark.measureNativeArrayOfStringsAccess

```