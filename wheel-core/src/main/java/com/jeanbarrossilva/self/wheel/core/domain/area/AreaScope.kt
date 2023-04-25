package com.jeanbarrossilva.self.wheel.core.domain.area

import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDoScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.utils.get

abstract class AreaScope(private val wheelScope: WheelScope, private val area: Area) {
    protected var name = area.name
        private set
    protected var attention = area.attention
        private set
    protected var toDos = area.toDos
        private set

    fun setName(name: String): AreaScope {
        return apply {
            this.name = name
        }
    }

    fun setAttention(attention: Float): AreaScope {
        return apply {
            this.attention = attention
        }
    }

    fun addToDo(title: String): AreaScope {
        return apply {
            toDos = toDos + ToDo(title)
        }
    }

    suspend fun onToDo(title: String): ToDoScope {
        val toDo = recreate().area.toDos[title]
        return toDo?.let(::createToDoScope) ?: throw IllegalArgumentException(
            "Cannot edit a nonexistent \"$title\" to-do in the \"${area.name}\" area."
        )
    }

    suspend fun submit(): AreaScope {
        onSubmission()
        return recreate()
    }

    protected abstract fun createToDoScope(toDo: ToDo): ToDoScope

    protected abstract suspend fun onSubmission()

    private suspend fun recreate(): AreaScope {
        return wheelScope.onArea(name)
    }
}
