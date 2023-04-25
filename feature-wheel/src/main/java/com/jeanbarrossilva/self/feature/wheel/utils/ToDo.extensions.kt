package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo

internal fun ToDo.feature(): FeatureToDo {
    return FeatureToDo(title, isDone)
}
