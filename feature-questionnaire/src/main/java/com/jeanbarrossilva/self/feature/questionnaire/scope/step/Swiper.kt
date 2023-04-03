package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import androidx.viewpager2.widget.ViewPager2

internal class Swiper(private val viewPager2: ViewPager2) {
    fun swipeBackwards() {
        val isNotAtFirstItem = viewPager2.currentItem != 0
        if (isNotAtFirstItem) {
            viewPager2.setCurrentItem(viewPager2.currentItem - 1, true)
        }
    }

    fun swipeForward() {
        val lastIndex = viewPager2.adapter?.itemCount?.minus(1) ?: 0
        val currentItem = viewPager2.currentItem
        val isNotAtTrailingItem = currentItem != lastIndex
        if (isNotAtTrailingItem) {
            viewPager2.setCurrentItem(currentItem + 1, true)
        }
    }
}
