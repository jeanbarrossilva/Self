package com.jeanbarrossilva.self.feature.wheel.utils

import android.content.res.Resources
import android.graphics.Typeface
import androidx.annotation.FontRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.res.ResourcesCompat

@Composable
internal fun typefaceResource(@FontRes id: Int): Typeface {
    return ResourcesCompat.getFont(LocalContext.current, id)
        ?: throw Resources.NotFoundException("$id")
}
