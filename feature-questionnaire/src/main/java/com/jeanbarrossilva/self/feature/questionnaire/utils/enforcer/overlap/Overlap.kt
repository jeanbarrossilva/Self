package com.jeanbarrossilva.self.feature.questionnaire.utils.enforcer.overlap

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets
import com.jeanbarrossilva.self.feature.questionnaire.utils.space

internal sealed class Overlap {
    abstract val amount: Int

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
                layoutParams.topMargin = it
            }
        }
    }

    data class Right(override val amount: Int) : Overlap() {
        override fun compensate(view: View, layoutParams: ViewGroup.MarginLayoutParams) {
            animate(view, layoutParams, layoutParams.rightMargin) {
                layoutParams.rightMargin = it
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
        target: Int,
        onChange: ViewGroup.MarginLayoutParams.(amount: Int) -> Unit
    ) {
        val animator = ObjectAnimator.ofInt(amount, target)
        animator.addUpdateListener {
            view.layoutParams = layoutParams.apply {
                onChange(it.animatedValue as Int)
            }
        }
        animator.start()
    }

    companion object {
        fun from(insets: List<Insets>, view: View): List<Overlap> {
            return insets.flatMap {
                from(it, view)
            }
        }

        private fun from(insets: Insets, view: View): List<Overlap> {
            val overlaps = mutableListOf<Overlap>()
            if (view.space.left < insets.left) {
                overlaps.add(Left(view.space.left + insets.left))
            }
            if (view.space.top < insets.top) {
                overlaps.add(Top(view.space.top + insets.top))
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
