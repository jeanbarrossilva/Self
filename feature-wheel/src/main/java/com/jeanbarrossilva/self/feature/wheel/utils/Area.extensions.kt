package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo

internal fun Area.feature(): FeatureArea {
    val toDos = toDos.map(ToDo::feature)
    return FeatureArea(name, attention, toDos)
}
