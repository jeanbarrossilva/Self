package com.jeanbarrossilva.self.feature.questionnaire.utils.view

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnLayout
import com.jeanbarrossilva.self.feature.questionnaire.utils.view.overlap.Overlap
import com.jeanbarrossilva.self.platform.ui.utils.rootWindowInsetsCompat

internal fun View.applySystemWindowInsetsAsMargin(
    buildLayoutParams: (width: Int, height: Int) -> MarginLayoutParams
) {
    doOnLayout {
        val windowInsets = rootWindowInsetsCompat ?: return@doOnLayout
        val systemBarsTypeMask = WindowInsetsCompat.Type.systemBars()
        val systemBarsInsets = windowInsets.getInsets(systemBarsTypeMask)
        val navigationBarsTypeMask = WindowInsetsCompat.Type.navigationBars()
        val navigationBarsInsets = windowInsets.getInsets(navigationBarsTypeMask)
        val systemInsets = listOf(systemBarsInsets, navigationBarsInsets)
        val viewSize = ViewGroup.LayoutParams.MATCH_PARENT
        val overlaps = systemInsets.flatMap { Overlap.from(systemInsets, this) }
        layoutParams = buildLayoutParams(viewSize, viewSize).apply {
            overlaps.forEach {
                it.compensate(this)
            }
        }
    }
}
