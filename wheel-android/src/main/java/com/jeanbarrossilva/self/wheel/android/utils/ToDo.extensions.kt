package com.jeanbarrossilva.self.wheel.android.utils

import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoEntity
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo

internal fun ToDo.domain(grandparent: String, parent: String): AndroidToDoEntity {
    return AndroidToDoEntity(id = 0, grandparent, parent, title, isDone)
}
