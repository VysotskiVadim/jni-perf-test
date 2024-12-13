package dev.vadzimv.jniperftest

object PlatformDataProvider {

    private lateinit var arrayOfStrings: Array<String>
    private lateinit var intArray: IntArray
    private lateinit var doubleArray: DoubleArray
    private lateinit var byteArray: ByteArray

    fun initStringArray(size: Int) {
        arrayOfStrings = Array(size) {
            "test string $it"
        }
    }

    fun getStringFromArray(index: Int) = arrayOfStrings[index]

    fun initIntArray(size: Int) {
        intArray = IntArray(size) {
            it
        }
    }

    fun getIntFromArray(index: Int) = intArray[index]

    fun initDoubleArray(size: Int) {
        doubleArray = DoubleArray(size) {
            it.toDouble() + 0.1
        }
    }

    fun getFromDoubleArray(index: Int) = doubleArray[index]

    fun initByteArray(size: Int) {
        byteArray = ByteArray(size)
    }

    fun getFromByteArray(index: Int): Byte = byteArray[index]
}