package com.jeanbarrossilva.self.platform.ui.utils

import androidx.fragment.app.Fragment
import com.jeanbarrossilva.self.platform.ui.core.ime.ImeController

val Fragment.imeController: ImeController?
    get() = view?.let(::ImeController)
