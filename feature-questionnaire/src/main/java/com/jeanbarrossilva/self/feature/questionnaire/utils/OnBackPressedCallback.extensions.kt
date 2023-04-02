package com.jeanbarrossilva.self.feature.questionnaire.utils

import androidx.activity.OnBackPressedCallback

internal fun OnBackPressedCallback(
    isEnabled: Boolean = true,
    handleOnBackPressed: () -> Unit = { }
): OnBackPressedCallback {
    return object : OnBackPressedCallback(isEnabled) {
        override fun handleOnBackPressed() {
            handleOnBackPressed()
        }
    }
}
