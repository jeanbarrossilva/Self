package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.foundation.layout.PaddingValues

internal val PaddingValues.vertical
    get() = calculateTopPadding() + calculateBottomPadding()
