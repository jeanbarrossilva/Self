package com.jeanbarrossilva.self.platform.ui.utils

import androidx.fragment.app.Fragment
import com.jeanbarrossilva.self.platform.ui.core.ime.ImeController

val Fragment.imeController: ImeController?
    get() {
        val window = activity?.window
        val view = view
        return if (window != null && view != null) ImeController(window, view) else null
    }
