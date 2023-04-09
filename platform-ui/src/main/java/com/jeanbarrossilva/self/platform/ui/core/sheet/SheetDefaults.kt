package com.jeanbarrossilva.self.platform.ui.core.sheet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme

object SheetDefaults {
    val padding
        @Composable get() = PaddingValues(SelfTheme.sizes.spacing.huge)
}
