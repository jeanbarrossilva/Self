package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize

internal val RoundedCornerShape.top
    get() = copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize)
