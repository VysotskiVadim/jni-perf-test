package dev.vadzimv.jniperftest

object PlatformDataProvider {

    private lateinit var arrayOfStrings: List<String>

    fun initArray(size: Int) {
        arrayOfStrings = MutableList(size) {
            "test string $it"
        }
    }

    fun getStringFromArray(index: Int) = arrayOfStrings[index]
}