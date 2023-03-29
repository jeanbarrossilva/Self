package com.jeanbarrossilva.self.platform.ui.core.back

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun BackPressedEffect(isEnabled: Boolean = true, effect: () -> Unit) {
    val activity = LocalContext.current as? ComponentActivity
    val callback = remember(isEnabled) {
        object : OnBackPressedCallback(isEnabled) {
            override fun handleOnBackPressed() {
                effect()
            }
        }
    }

    DisposableEffect(callback) {
        activity?.onBackPressedDispatcher?.addCallback(callback)
        onDispose(callback::remove)
    }
}
