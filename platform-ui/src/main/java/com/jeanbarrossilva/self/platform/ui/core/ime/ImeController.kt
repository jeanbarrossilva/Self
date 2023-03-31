package com.jeanbarrossilva.self.platform.ui.core.ime

import android.view.View
import com.jeanbarrossilva.self.platform.ui.utils.rootWindowInsetsCompat
import com.jeanbarrossilva.self.platform.ui.utils.windowInsetsControllerCompat

class ImeController internal constructor(private val view: View) {
    val imeState
        get() = ImeState.from(view.rootWindowInsetsCompat)

    fun open() {
        if (imeState != ImeState.OPEN) {
            view.windowInsetsControllerCompat?.show(ImeState.type)
        }
    }

    fun close() {
        if (imeState != ImeState.CLOSED) {
            view.windowInsetsControllerCompat?.hide(ImeState.type)
        }
    }
}
