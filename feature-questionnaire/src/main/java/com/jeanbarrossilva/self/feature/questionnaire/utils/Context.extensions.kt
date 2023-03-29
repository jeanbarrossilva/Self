package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

internal val Context.preferences: SharedPreferences?
    get() = PreferenceManager.getDefaultSharedPreferences(this)
