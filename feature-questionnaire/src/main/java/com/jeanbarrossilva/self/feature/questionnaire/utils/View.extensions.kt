package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.view.View
import android.view.ViewGroup
import com.jeanbarrossilva.self.feature.questionnaire.utils.enforcer.SystemWindowInsetsEnforcer

internal fun View.enforceSystemWindowInsets(
    buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
) {
    SystemWindowInsetsEnforcer.enforce(this, buildLayoutParams)
}
