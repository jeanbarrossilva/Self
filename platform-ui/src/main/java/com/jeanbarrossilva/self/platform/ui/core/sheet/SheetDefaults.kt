package com.jeanbarrossilva.self.platform.ui.core.sheet

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.elevated

object SheetDefaults {
    val padding
        @Composable get() = PaddingValues(SelfTheme.sizes.spacing.huge)
    val containerColor
        @Composable get() = SelfTheme.colors.elevated
}
