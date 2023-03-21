package com.jeanbarrossilva.self.feature.wheel.domain

import java.io.Serializable

data class FeatureToDo internal constructor(val title: String, val isDone: Boolean) : Serializable
