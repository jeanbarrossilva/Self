package com.jeanbarrossilva.self.wheel.core.domain.todo

import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope

abstract class ToDoScope(private val areaScope: AreaScope, toDo: ToDo) {
    protected var title = toDo.title
        private set
    protected var isDone = toDo.isDone
        private set

    fun setTitle(title: String): ToDoScope {
        return apply {
            this.title = title
        }
    }

    fun setDone(isDone: Boolean): ToDoScope {
        return apply {
            this.isDone = isDone
        }
    }

    suspend fun submit(): ToDoScope {
        onSubmission()
        return areaScope.onToDo(title)
    }

    protected abstract suspend fun onSubmission()
}
