package com.jeanbarrossilva.self.platform.ui.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

val Context.preferences: SharedPreferences?
    get() = PreferenceManager.getDefaultSharedPreferences(this)
