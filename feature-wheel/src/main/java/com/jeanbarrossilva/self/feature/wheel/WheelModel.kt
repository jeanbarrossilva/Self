package com.jeanbarrossilva.self.feature.wheel

import java.text.NumberFormat
import java.util.Locale

object WheelModel {
    fun format(attention: Float): String {
        val locale = Locale.getDefault()
        return NumberFormat.getPercentInstance(locale).apply { maximumFractionDigits = 1 }.format(
            attention
        )
    }
}
