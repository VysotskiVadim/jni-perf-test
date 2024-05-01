package dev.vadzimv.jniperftest.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.vadzimv.jniperftest.app.ui.theme.JNIPerfTestTheme
import androidx.lifecycle.viewmodel.compose.viewModel


class MainScreenActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JNIPerfTestTheme {
                val mainScreenViewModel: MainScreenViewModel = viewModel()
                val screenState = mainScreenViewModel.state.collectAsState()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen(screenState.value) {
                        mainScreenViewModel.performAction(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Screen(state: ScreenState, performAction: (MainScreenAction) -> Unit) {
    Column {
        Row {
            Button(
                onClick = { performAction(MainScreenAction.AllocateNative) },
                enabled = state.canAllocateNative,
            ) {
                Text(text = "Allocate Native")
            }
            Button(
                onClick = { performAction(MainScreenAction.ProcessNative)},
                enabled = state.canProcessNative
            ) {
                Text(text = "Process Native")
            }
        }
        Row {
            Button(
                onClick = { performAction(MainScreenAction.AllocatePlatform) },
                enabled = state.canAllocatePlatform
            ) {
                Text(text = "Allocate platform")
            }
            Button(
                onClick = { performAction(MainScreenAction.ProcessPlatform) },
                enabled = state.canProcessPlatform
            ) {
                Text(text = "Process Platform")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInitial() {
    JNIPerfTestTheme {
        Screen(
            ScreenState()
        ) { }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNativeAllocated() {
    JNIPerfTestTheme {
        Screen(
            ScreenState(
                canAllocateNative = false,
                canProcessNative = true,
            )
        ) { }
    }
}