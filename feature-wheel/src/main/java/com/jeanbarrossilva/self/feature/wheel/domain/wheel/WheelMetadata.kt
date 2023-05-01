package com.jeanbarrossilva.self.feature.wheel.domain.wheel

import androidx.compose.ui.semantics.SemanticsPropertyKey

data class WheelMetadata(val x: HashSet<String>, val y: HashSet<ClosedFloatingPointRange<Float>>) {
    fun withX(x: String): WheelMetadata {
        val appendedX = this.x.plus(x).toHashSet()
        return copy(x = appendedX)
    }

    fun withY(y: Float): WheelMetadata {
        val lastY = this.y.lastOrNull()?.endInclusive ?: 0f
        val appendedY = this.y.plus(lastY..y).toHashSet()
        return copy(y = appendedY)
    }

    companion object {
        val SemanticsPropertyKey = SemanticsPropertyKey<WheelMetadata>("ChartMetadata")

        val empty = WheelMetadata(x = hashSetOf(), y = hashSetOf())
    }
}
