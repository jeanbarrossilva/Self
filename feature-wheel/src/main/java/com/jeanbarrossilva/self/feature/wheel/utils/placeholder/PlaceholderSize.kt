package com.jeanbarrossilva.self.feature.wheel.utils.placeholder

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.jeanbarrossilva.self.feature.wheel.utils.`if`

abstract class PlaceholderSize protected constructor() {
    @get:Composable
    protected abstract val width: Dp

    @get:Composable
    protected abstract val height: Dp

    internal val modifier = Modifier
        .`if`({ width.isSpecified }) { width(width) }
        .`if`({ height.isSpecified }) { height(height) }

    data class Text(
        private val textStyle: @Composable () -> TextStyle = { LocalTextStyle.current },
        @FloatRange(from = 0.0, to = 1.0) private val fraction: Float = .4f
    ) : PlaceholderSize() {
        private val density
            @Composable get() = LocalDensity.current

        override val width
            @Composable get() =
                LocalContext.current.resources.configuration.screenWidthDp.dp / (fraction * 10)

        override val height
            @Composable get() = with(density) { textStyle().fontSize.toDp() }
    }

    companion object {
        infix fun of(value: Dp): PlaceholderSize {
            return object: PlaceholderSize() {
                override val width
                    @Composable get() = value

                override val height
                    @Composable get() = value
            }
        }
    }
}
