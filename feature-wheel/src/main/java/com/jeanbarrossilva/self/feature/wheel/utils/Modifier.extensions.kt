package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import com.jeanbarrossilva.aurelius.utils.`if`
import com.jeanbarrossilva.self.feature.wheel.core.placeholder.PlaceholderSize
import com.jeanbarrossilva.self.platform.ui.theme.SelfTheme
import com.jeanbarrossilva.self.platform.ui.utils.placeholder

fun Modifier.`if`(
    condition: @Composable Modifier.() -> Boolean,
    update: @Composable Modifier.() -> Modifier
): Modifier {
    @Suppress("UnnecessaryComposedModifier")
    return composed {
        if (condition()) update() else this
    }
}

fun Modifier.placeholder(size: PlaceholderSize, isVisible: Boolean): Modifier {
    return composed {
        placeholder(size, SelfTheme.shapes.medium, isVisible)
    }
}

fun Modifier.placeholder(size: PlaceholderSize, shape: Shape, isVisible: Boolean):
    Modifier {
    return composed {
        placeholder(
            isVisible,
            SelfTheme.colors.placeholder,
            shape,
            PlaceholderHighlight.shimmer()
        )
            .`if`(isVisible) { then(size.modifier) }
    }
}
