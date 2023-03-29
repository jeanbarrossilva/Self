package com.jeanbarrossilva.self.feature.questionnaire.domain.attention

import androidx.annotation.IntRange
import com.jeanbarrossilva.self.wheel.core.domain.Area

@IntRange(from = AttentionPercent.MIN.toLong(), to = AttentionPercent.MAX.toLong())
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
internal annotation class AttentionPercent {
    companion object {
        const val MIN = (Area.MIN_ATTENTION * 100).toInt()
        const val MAX = (Area.MAX_ATTENTION * 100).toInt()
    }
}
