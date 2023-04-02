package com.jeanbarrossilva.self.feature.questionnaire.utils

import androidx.core.graphics.Insets

internal val Insets.isZeroed
    get() = left == 0 && top == 0 && right == 0 && bottom == 0

internal operator fun Insets.plus(other: Insets): Insets {
    return Insets(left + other.left, top + other.top, right + other.right, bottom + other.bottom)
}

internal fun Insets(left: Int = 0, top: Int = 0, right: Int = 0, bottom: Int = 0): Insets {
    return Insets.of(left, top, right, bottom)
}
