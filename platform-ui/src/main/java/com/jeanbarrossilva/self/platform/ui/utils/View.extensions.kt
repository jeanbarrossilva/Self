package com.jeanbarrossilva.self.platform.ui.utils

import android.view.View

val View.rootWindowInsetsCompat
    get() = rootWindowInsets?.toWindowInsetsCompat(this)
