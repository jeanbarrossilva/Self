package com.jeanbarrossilva.self.platform.ui.core.ime

import androidx.core.view.WindowInsetsCompat

enum class ImeState {
    OPEN,
    CLOSED,
    UNKNOWN;

    companion object {
        internal val type = WindowInsetsCompat.Type.ime()

        internal fun from(windowInsets: WindowInsetsCompat?): ImeState {
            return when {
                windowInsets == null -> UNKNOWN
                windowInsets.isVisible(type) -> OPEN
                else -> CLOSED
            }
        }
    }
}
