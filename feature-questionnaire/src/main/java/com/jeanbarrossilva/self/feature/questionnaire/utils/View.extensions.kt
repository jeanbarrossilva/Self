package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.app.Activity
import android.graphics.Rect
import android.view.View
import androidx.core.view.marginBottom
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.marginTop

/** [Rect] in which this [View]'s contents are drawn that disregards padding and margin. **/
internal val View.space
    get() = Rect(
        left + paddingLeft + marginLeft,
        top + paddingTop + marginTop,
        right - paddingRight - marginRight,
        bottom - paddingBottom - marginBottom
    )

internal val View.window
    get() = (context as? Activity)?.window

internal fun View.doOnDetach(block: (View) -> Unit) {
    addOnAttachStateChangeListener(
        object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
            }

            override fun onViewDetachedFromWindow(v: View) {
                block(v)
            }
        }
    )
}
