package com.jeanbarrossilva.self.feature.questionnaire.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.aurelius.utils.end
import com.jeanbarrossilva.aurelius.utils.start

internal val PaddingValues.inverted
    @Composable get() = PaddingValues(
        start = end,
        calculateTopPadding(),
        end = start,
        calculateBottomPadding()
    )
