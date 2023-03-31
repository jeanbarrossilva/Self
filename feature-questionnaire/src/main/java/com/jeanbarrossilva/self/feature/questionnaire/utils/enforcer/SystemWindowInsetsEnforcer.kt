package com.jeanbarrossilva.self.feature.questionnaire.utils.enforcer

import android.app.Activity
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.jeanbarrossilva.self.feature.questionnaire.utils.enforcer.overlap.Overlap
import com.jeanbarrossilva.self.feature.questionnaire.utils.space
import com.jeanbarrossilva.self.platform.ui.utils.rootWindowInsetsCompat
import com.jeanbarrossilva.self.platform.ui.utils.toWindowInsetsCompat
import com.jeanbarrossilva.self.platform.ui.utils.windowInsetsControllerCompat
import kotlin.time.Duration.Companion.milliseconds
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

internal object SystemWindowInsetsEnforcer {
    private const val TAG = "SystemWindowInsetsEnforcer"

    fun enforce(
        view: View,
        buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
    ) {
        enforceOnRootViewChanges(view, buildLayoutParams)
        enforceAfterResumed(view, buildLayoutParams)
        enforceOnInsetsChange(view, buildLayoutParams)
    }

    private fun enforceOnRootViewChanges(
        view: View,
        buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
    ) {
        (view.context as? Activity)
            ?.window
            ?.decorView
            ?.rootView
            ?.setOnApplyWindowInsetsListener { _view, insets ->
                val insetsCompat = insets.toWindowInsetsCompat(_view)
                enforce(_view, insetsCompat, buildLayoutParams)
                insets
            }
    }

    private fun enforceAfterResumed(
        view: View,
        buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
    ) {
        view.findViewTreeLifecycleOwner()?.apply {
            lifecycleScope.launch {
                repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    view.rootWindowInsetsCompat?.let {
                        delay(512.milliseconds)
                        enforce(view, it, buildLayoutParams)
                    }
                }
            }
        }
    }

    private fun enforceOnInsetsChange(
        view: View,
        buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
    ) {
        view.windowInsetsControllerCompat?.addOnControllableInsetsChangedListener { _, _ ->
            view.rootWindowInsetsCompat?.let {
                enforce(view, it, buildLayoutParams)
            }
        }
    }

    private fun enforce(
        view: View,
        windowInsets: WindowInsetsCompat,
        buildLayoutParams: (width: Int, height: Int) -> ViewGroup.MarginLayoutParams
    ) {
        val systemBarsTypeMask = WindowInsetsCompat.Type.systemBars()
        val systemBarsInsets = windowInsets.getInsets(systemBarsTypeMask)
        val navigationBarsTypeMask = WindowInsetsCompat.Type.navigationBars()
        val navigationBarsInsets = windowInsets.getInsets(navigationBarsTypeMask)
        val systemInsets = listOf(systemBarsInsets, navigationBarsInsets)
        val viewSize = ViewGroup.LayoutParams.MATCH_PARENT
        val overlaps = systemInsets.flatMap { Overlap.from(systemInsets, view) }
        view.layoutParams = buildLayoutParams(viewSize, viewSize).apply {
            overlaps.forEach {
                it.compensate(view, this)
            }
        }
        Log.d(TAG, "enforce view.space: ${view.space}")
    }
}
