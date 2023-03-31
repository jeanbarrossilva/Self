package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop
import com.jeanbarrossilva.self.feature.questionnaire.utils.enforcer.SystemWindowInsetsEnforcer

/** [Rect] in which this [View]'s contents are drawn that disregards padding and margin. **/
internal val View.space
    get() = Rect(
        left + paddingLeft + marginLeft,
        top + paddingTop + marginTop,
        right - paddingRight - marginRight,
        bottom - paddingBottom - marginBottom
    )

internal fun View.enforceSystemWindowInsets(
    buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
) {
    SystemWindowInsetsEnforcer.enforce(this, buildLayoutParams)
}
