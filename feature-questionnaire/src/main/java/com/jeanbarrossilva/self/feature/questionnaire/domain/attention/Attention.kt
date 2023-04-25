package com.jeanbarrossilva.self.feature.questionnaire.domain.attention

import androidx.annotation.FloatRange
import com.jeanbarrossilva.self.wheel.core.domain.area.Area

@Target(
    AnnotationTarget.PROPERTY,
    AnnotationTarget.VALUE_PARAMETER,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.TYPE
)
@FloatRange(from = Area.MIN_ATTENTION.toDouble(), to = Area.MAX_ATTENTION.toDouble())
internal annotation class Attention
