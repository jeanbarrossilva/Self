package com.jeanbarrossilva.self.platform.ui.utils

import android.view.View
import android.view.WindowInsets
import androidx.core.view.WindowInsetsCompat

fun WindowInsets.toWindowInsetsCompat(view: View?): WindowInsetsCompat {
    return WindowInsetsCompat.toWindowInsetsCompat(this, view)
}
