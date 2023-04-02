package com.jeanbarrossilva.self.feature.questionnaire.utils.insets

import android.content.res.Resources
import android.util.Log
import android.view.ViewGroup
import androidx.core.graphics.Insets
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.self.feature.questionnaire.utils.Insets
import com.jeanbarrossilva.self.feature.questionnaire.utils.dimension.SystemBarDimension
import com.jeanbarrossilva.self.feature.questionnaire.utils.dimension.SystemBarDimensionGetter
import com.jeanbarrossilva.self.feature.questionnaire.utils.doOnPageSelected
import com.jeanbarrossilva.self.feature.questionnaire.utils.insets.overlap.Overlap
import com.jeanbarrossilva.self.feature.questionnaire.utils.isZeroed
import com.jeanbarrossilva.self.feature.questionnaire.utils.layoutParamsOf
import com.jeanbarrossilva.self.feature.questionnaire.utils.plus
import com.jeanbarrossilva.self.feature.questionnaire.utils.window
import com.jeanbarrossilva.self.platform.ui.utils.rootWindowInsetsCompat
import kotlinx.coroutines.launch

internal object SystemWindowInsetsEnforcer {
    private const val TAG = "SystemWindowInsetsEnforcer"

    inline fun <reified T : ViewGroup.MarginLayoutParams> enforce(viewPager: ViewPager2) {
        // System window insets get ignored after the page's been resumed to for the second time.
        enforceAfterResumed<T>(viewPager)

        // One scenario in which this is essential is when the IME was visible on the previous page
        // and continues to do so after the user's been taken to the next one. If we don't dispatch
        // the up-to-date window insets applied to the root view and the IME continues to be visible
        // on the next page, system-window-insets-aware UI components (such as a Composable with
        // Modifier.imePadding) won't be placed correctly.
        dispatchOnPageSelection(viewPager)
    }

    private inline fun <reified T : ViewGroup.MarginLayoutParams> enforceAfterResumed(
        viewPager2: ViewPager2
    ) {
        viewPager2.findViewTreeLifecycleOwner()?.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    viewPager2.rootWindowInsetsCompat?.let {
                        enforce<T>(viewPager2, it)
                    }
                }
            }
        }
    }

    private fun dispatchOnPageSelection(viewPager2: ViewPager2) {
        viewPager2.doOnPageSelected {
            viewPager2
                .window
                ?.decorView
                ?.rootView
                ?.rootWindowInsetsCompat
                ?.toWindowInsets()
                ?.let(viewPager2::dispatchApplyWindowInsets)
        }
    }

    private inline fun <reified T : ViewGroup.MarginLayoutParams> enforce(
        viewPager2: ViewPager2,
        windowInsets: WindowInsetsCompat
    ) {
        try {
            updateLayoutParams<T>(viewPager2, windowInsets)
        } catch (_: NoSuchMethodException) {
            Log.e(TAG, "Couldn't update ViewPager2's LayoutParams.")
        }
    }

    private inline fun <reified T : ViewGroup.MarginLayoutParams> updateLayoutParams(
        viewPager2: ViewPager2,
        windowInsets: WindowInsetsCompat
    ) {
        val resources = viewPager2.context?.resources
        val systemBarsTypeMask = WindowInsetsCompat.Type.systemBars()
        val systemBarsInsets = windowInsets
            .getInsets(systemBarsTypeMask)
            .`if`(Insets::isZeroed) {
                Insets(top = SystemBarDimensionGetter.getStatusBar(resources).value) +
                    getNavigationBarInsets(resources)
            }
        val viewSize = ViewGroup.LayoutParams.MATCH_PARENT
        val overlaps = Overlap.from(systemBarsInsets, viewPager2)
        viewPager2.layoutParams = layoutParamsOf<T>(viewSize).apply {
            overlaps.forEach {
                it.compensate(viewPager2, this)
            }
        }
    }

    private fun getNavigationBarInsets(resources: Resources?): Insets {
        return when (val dimension = SystemBarDimensionGetter.getNavigationBar(resources)) {
            is SystemBarDimension.Width -> Insets(left = dimension.value)
            is SystemBarDimension.Height -> Insets(bottom = dimension.value)
            else -> Insets()
        }
    }
}
