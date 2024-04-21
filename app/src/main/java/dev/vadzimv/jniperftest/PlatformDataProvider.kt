package dev.vadzimv.jniperftest

object PlatformDataProvider {
    private val arrayOfStrings = MutableList(10_000) {
        it.toString()
    }

    fun getStringFromArray(index: Int) = arrayOfStrings[index]
}