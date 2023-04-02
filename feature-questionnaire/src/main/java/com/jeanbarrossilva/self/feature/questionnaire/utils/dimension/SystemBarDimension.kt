package com.jeanbarrossilva.self.feature.questionnaire.utils.dimension

internal interface SystemBarDimension {
    val value: Int

    data class Width(override val value: Int) : SystemBarDimension

    data class Height(override val value: Int) : SystemBarDimension
}
