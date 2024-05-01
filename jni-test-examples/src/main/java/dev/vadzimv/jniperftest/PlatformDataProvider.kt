package dev.vadzimv.jniperftest

object PlatformDataProvider {

    private lateinit var arrayOfStrings: Array<String>
    private lateinit var intArray: IntArray

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
}