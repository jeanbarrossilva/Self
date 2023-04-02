package com.jeanbarrossilva.self.feature.questionnaire.utils.insets.overlap

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import com.jeanbarrossilva.self.feature.questionnaire.utils.space

internal sealed class Overlap {
    protected abstract val amount: Int

    data class Left(override val amount: Int) : Overlap() {
        override fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams) {
            animate(view, layoutParams, layoutParams.leftMargin) {
                layoutParams.leftMargin = it
            }
        }
    }

    data class Top(override val amount: Int) : Overlap() {
        override fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams) {
            animate(view, layoutParams, layoutParams.topMargin) {
                topMargin = it
            }
        }
    }

    data class Right(override val amount: Int) : Overlap() {
        override fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams) {
            animate(view, layoutParams, layoutParams.rightMargin) {
                rightMargin = it
            }
        }
    }

    data class Bottom(override val amount: Int) : Overlap() {
        override fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams) {
            animate(view, layoutParams, layoutParams.bottomMargin) {
                bottomMargin = it
            }
        }
    }

    abstract fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams)

    protected fun animate(
        view: View,
        layoutParams: ViewGroup.MarginLayoutParams,
        origin: Int,
        onChange: ViewGroup.MarginLayoutParams.(Int) -> Unit
    ) {
        val animator = ObjectAnimator.ofInt(origin, amount)
        animator.addUpdateListener {
            view.layoutParams = layoutParams.apply {
                onChange(it.animatedValue as Int)
            }
        }
        animator.start()
    }

    companion object {
        fun from(insets: Insets, view: View): List<Overlap> {
            val overlaps = mutableListOf<Overlap>()
            if (view.space.left < insets.left) {
                overlaps.add(Left(insets.left - view.space.left))
            }
            if (view.space.top < insets.top) {
                overlaps.add(Top(insets.top - view.space.top))
            }
            if (view.space.right > insets.right) {
                overlaps.add(Right(view.space.right - insets.right))
            }
            if (view.space.bottom > insets.bottom) {
                overlaps.add(Bottom(view.space.bottom - insets.bottom))
            }
            return overlaps.toList()
        }
    }
}
