package dev.vadzimv.jniperftest

object PlatformDataProvider {

    private lateinit var arrayOfStrings: List<String>

    fun initArray() {
        arrayOfStrings = MutableList(10_000) {
            "test string $it"
        }
    }

    fun getStringFromArray(index: Int) = arrayOfStrings[index]
}