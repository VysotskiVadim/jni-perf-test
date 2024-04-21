package dev.vadzimv.jniperftest

object PlatformDataProvider {
    private val arrayOfStrings = MutableList(10_000) {
        "test string $it"
    }

    fun getStringFromArray(index: Int) = arrayOfStrings[index]
}