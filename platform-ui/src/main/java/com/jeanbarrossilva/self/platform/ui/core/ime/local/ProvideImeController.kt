package com.jeanbarrossilva.self.platform.ui.core.ime.local

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import com.jeanbarrossilva.self.platform.ui.core.ime.ImeController

@Composable
internal fun ProvideImeController(content: @Composable () -> Unit) {
    val activity = LocalContext.current as? Activity
    val view = LocalView.current
    val imeController = remember(activity, view) { ImeController(view) }

    CompositionLocalProvider(LocalImeController provides imeController, content = content)
}
