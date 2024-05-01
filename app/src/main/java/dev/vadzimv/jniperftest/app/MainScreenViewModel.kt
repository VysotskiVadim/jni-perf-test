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
                state.update {
                    it.copy(
                        canAllocateNative = false,
                        canProcessNative = true
                    )
                }
                NativeDataProvider.initStringArray(arraySize)
            }
            MainScreenAction.AllocatePlatform -> {
                state.update {
                    it.copy(
                        canAllocatePlatform = false,
                        canProcessPlatform = true
                    )
                }
                PlatformDataProvider.initStringArray(arraySize)
            }
            MainScreenAction.ProcessNative -> {
                for (i in 0 until arraySize) {
                    NativeDataProvider.getNativeStringFromArray(i)
                }
            }
            MainScreenAction.ProcessPlatform -> {
                for (i in 0 until arraySize) {
                    PlatformDataProvider.getStringFromArray(i)
                }
            }
        }
    }
}

sealed class MainScreenAction {
    object AllocateNative: MainScreenAction()
    object ProcessNative: MainScreenAction()
    object AllocatePlatform: MainScreenAction()
    object ProcessPlatform: MainScreenAction()
}

data class ScreenState(
    val canAllocatePlatform: Boolean = true,
    val canProcessPlatform: Boolean = false,
    val canAllocateNative: Boolean = true,
    val canProcessNative: Boolean = false
)
