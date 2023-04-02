package com.jeanbarrossilva.self.feature.questionnaire.utils.dimension

import android.content.res.Configuration
import android.content.res.Resources
import androidx.annotation.DimenRes
import com.jeanbarrossilva.aurelius.utils.`if`

internal object SystemBarDimensionGetter {
    fun getNavigationBar(resources: Resources?): SystemBarDimension {
        val isInLandscape =
            resources?.configuration?.orientation == Configuration.ORIENTATION_LANDSCAPE
        val resourceName = "navigation_bar_height".`if`(isInLandscape) { plus("_landscape") }
        val value = getPrivateDimensionResourceID(resources, resourceName)
        return if (isInLandscape) {
            SystemBarDimension.Width(value)
        } else {
            SystemBarDimension.Height(value)
        }
    }

    fun getStatusBar(resources: Resources?): SystemBarDimension {
        val value = getPrivateDimensionResourceID(resources, "status_bar_height")
        return SystemBarDimension.Height(value)
    }

    @DimenRes
    @Suppress("DiscouragedApi")
    private fun getPrivateDimensionResourceID(resources: Resources?, name: String): Int {
        val id = resources?.getIdentifier(name, "dimen", "android") ?: 0
        return if (id > 0) resources?.getDimensionPixelSize(id) ?: 0 else 0
    }
}
