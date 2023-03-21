package com.jeanbarrossilva.self.feature.wheel.utils

import androidx.compose.ui.graphics.Color
import kotlin.random.Random

internal val Color.Companion.random
    get() = Color(
        red = getRandomColorChannelValue(),
        green = getRandomColorChannelValue(),
        blue = getRandomColorChannelValue()
    )

private fun getRandomColorChannelValue(): Int {
    return Random(0).nextInt(256)
}
