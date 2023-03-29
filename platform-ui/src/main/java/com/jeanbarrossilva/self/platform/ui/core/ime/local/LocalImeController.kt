package com.jeanbarrossilva.self.platform.ui.core.ime.local

import androidx.compose.runtime.compositionLocalOf
import com.jeanbarrossilva.self.platform.ui.core.ime.ImeController

val LocalImeController = compositionLocalOf<ImeController> {
    error("CompositionLocal LocalImeController not present.")
}
