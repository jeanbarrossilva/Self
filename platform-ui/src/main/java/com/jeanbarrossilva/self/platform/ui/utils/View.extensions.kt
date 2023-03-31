package com.jeanbarrossilva.self.platform.ui.utils

import android.app.Activity
import android.view.View
import androidx.core.view.WindowInsetsControllerCompat

val View.rootWindowInsetsCompat
    get() = rootWindowInsets?.toWindowInsetsCompat(this)
val View.windowInsetsControllerCompat
    get() = (context as? Activity)?.window?.let { WindowInsetsControllerCompat(it, this) }
