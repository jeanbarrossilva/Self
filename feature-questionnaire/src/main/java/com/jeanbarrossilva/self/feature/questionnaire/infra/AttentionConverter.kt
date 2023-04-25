package com.jeanbarrossilva.self.feature.questionnaire.infra

import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.AttentionPercent
import com.jeanbarrossilva.self.wheel.core.domain.area.Area

internal object AttentionConverter {
    @Attention
    fun core(attention: String): Float? {
        return percent(attention)?.let(::core)
    }

    @Attention
    fun core(@AttentionPercent attention: Int): Float {
        return attention.coerceIn(AttentionPercent.MIN..AttentionPercent.MAX).toFloat() / 100
    }

    @AttentionPercent
    fun percent(attention: String): Int? {
        return attention.toIntOrNull()
    }

    @AttentionPercent
    fun percent(@Attention attention: Float): Int {
        return (attention.coerceIn(Area.MIN_ATTENTION..Area.MAX_ATTENTION) * 100).toInt()
    }

    fun percentage(attention: String): String {
        return core(attention)?.let(::percentage).orEmpty()
    }

    fun percentage(@Attention attention: Float): String {
        return "${percent(attention)}%"
    }
}
