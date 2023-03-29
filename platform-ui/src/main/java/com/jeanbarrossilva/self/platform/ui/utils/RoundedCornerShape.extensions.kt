package com.jeanbarrossilva.self.platform.ui.utils

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize

internal val RoundedCornerShape.top
    get() = copy(bottomEnd = ZeroCornerSize, bottomStart = ZeroCornerSize)
