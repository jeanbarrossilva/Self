package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import androidx.viewpager2.widget.ViewPager2

internal class Swiper(private val viewPager: ViewPager2) {
    fun swipeBackwards() {
        val isNotAtFirstItem = viewPager.currentItem != 0
        if (isNotAtFirstItem) {
            viewPager.setCurrentItem(viewPager.currentItem - 1, true)
        }
    }

    fun swipeForward() {
        val lastIndex = viewPager.adapter?.itemCount ?: 0
        val isNotAtTrailingItem = viewPager.currentItem != lastIndex
        if (isNotAtTrailingItem) {
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }
    }
}
