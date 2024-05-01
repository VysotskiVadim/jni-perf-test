package dev.vadzimv.jniperftest.app

import androidx.lifecycle.ViewModel
import dev.vadzimv.jniperftest.NativeDataProvider
import dev.vadzimv.jniperftest.PlatformDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

const val arraySize = 1_000_000

class MainScreenViewModel : ViewModel() {
    val state = MutableStateFlow(ScreenState())
    fun performAction(action: MainScreenAction) {
        when(action) {
            MainScreenAction.AllocateNative -> {
                state.update { it.copy(canAllocateNative = false) }
                NativeDataProvider.initArray(arraySize)
            }
            MainScreenAction.AllocatePlatform -> {
                state.update { it.copy(canAllocatePlatform = false) }
                PlatformDataProvider.initArray(arraySize)
            }
        }
    }
}

sealed class MainScreenAction {
    object AllocateNative: MainScreenAction()
    object AllocatePlatform: MainScreenAction()
}

data class ScreenState(
    val canAllocatePlatform: Boolean = true,
    val canAllocateNative: Boolean = true,
)

private fun <T> MutableStateFlow<T>.updateState(update: (T) -> T) {
    value = update(value)
}