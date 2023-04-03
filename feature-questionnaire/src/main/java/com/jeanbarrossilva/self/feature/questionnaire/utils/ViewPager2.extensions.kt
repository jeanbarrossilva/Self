package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.jeanbarrossilva.self.feature.questionnaire.utils.insets.SystemWindowInsetsEnforcer

internal fun ViewPager2.doOnPageSelected(block: (position: Int) -> Unit) {
    val callback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            block(position)
        }
    }
    if (isAttachedToWindow) {
        registerOnPageChangeCallback(callback)
        doOnDetach { unregisterOnPageChangeCallback(callback) }
    } else {
        addOnAttachStateChangeListener(
            object : View.OnAttachStateChangeListener {
                override fun onViewAttachedToWindow(v: View) {
                    registerOnPageChangeCallback(callback)
                }

                override fun onViewDetachedFromWindow(v: View) {
                    unregisterOnPageChangeCallback(callback)
                }
            }
        )
    }
}

internal inline fun <reified T : ViewGroup.MarginLayoutParams> ViewPager2.enforceSystemWindowInsets() { // ktlint-disable max-line-length
    SystemWindowInsetsEnforcer.enforce<T>(this)
}
