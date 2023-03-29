package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import me.onebone.toolbar.CollapsingToolbarState

internal fun CollapsingToolbarState.fontSize(collapsed: TextUnit, expanded: TextUnit): TextUnit {
    return (collapsed.value + (expanded.value - collapsed.value) * progress).sp
}
