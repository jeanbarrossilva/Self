package com.jeanbarrossilva.self.feature.questionnaire.utils

import androidx.compose.ui.focus.FocusRequester

internal fun FocusRequester.tryToRequestFocus() {
    try {
        requestFocus()
    } catch (_: IllegalStateException) {
    }
}
