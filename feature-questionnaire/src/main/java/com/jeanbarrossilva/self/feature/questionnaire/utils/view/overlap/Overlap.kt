package com.jeanbarrossilva.self.feature.questionnaire.utils.view.overlap

import android.animation.ObjectAnimator
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.Insets

internal sealed class Overlap {
    abstract val amount: Int

    data class Left(override val amount: Int) : Overlap() {
        override fun compensate(layoutParams: ViewGroup.MarginLayoutParams) {
            animate(amount, layoutParams.leftMargin) {
                layoutParams.leftMargin = it
            }
        }
    }

    data class Top(override val amount: Int) : Overlap() {
        override fun compensate(layoutParams: ViewGroup.MarginLayoutParams) {
            animate(amount, layoutParams.topMargin) {
                layoutParams.topMargin = it
            }
        }
    }

    data class Right(override val amount: Int) : Overlap() {
        override fun compensate(layoutParams: ViewGroup.MarginLayoutParams) {
            animate(amount, layoutParams.rightMargin) {
                layoutParams.rightMargin = it
            }
        }
    }

    data class Bottom(override val amount: Int) : Overlap() {
        override fun compensate(layoutParams: ViewGroup.MarginLayoutParams) {
            animate(amount, layoutParams.bottomMargin) {
                layoutParams.bottomMargin = it
            }
        }
    }

    abstract fun compensate(layoutParams: ViewGroup.MarginLayoutParams)

    companion object {
        fun from(insets: List<Insets>, view: View): List<Overlap> {
            return insets.flatMap {
                from(it, view)
            }
        }

        private fun from(insets: Insets, view: View): List<Overlap> {
            val overlaps = mutableListOf<Overlap>()
            if (view.left < insets.left) overlaps.add(Left(insets.left))
            if (view.top < insets.top) overlaps.add(Top(insets.top))
            if (view.right > insets.right) overlaps.add(Right(insets.right))
            if (view.bottom > insets.bottom) overlaps.add(Bottom(insets.bottom))
            return overlaps.toList()
        }

        private fun animate(origin: Int, target: Int, onChange: (amount: Int) -> Unit) {
            val animator = ObjectAnimator.ofInt(origin, target)
            animator.addUpdateListener { onChange(it.animatedValue as Int) }
            animator.start()
        }
    }
}
