package com.jeanbarrossilva.self.platform.ui.core.ime

import android.view.View
import android.view.Window
import androidx.core.view.WindowInsetsControllerCompat
import com.jeanbarrossilva.self.platform.ui.utils.rootWindowInsetsCompat

class ImeController internal constructor(private val window: Window?, private val view: View) {
    private val windowInsetsController
        get() = window?.let { WindowInsetsControllerCompat(it, view) }

    val imeState
        get() = ImeState.from(view.rootWindowInsetsCompat)

    fun open() {
        if (imeState != ImeState.OPEN) {
            windowInsetsController?.show(ImeState.type)
        }
    }

    fun close() {
        if (imeState != ImeState.CLOSED) {
            windowInsetsController?.hide(ImeState.type)
        }
    }
}
