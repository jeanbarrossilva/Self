package com.jeanbarrossilva.self.wheel.inmemory.domain.todo

import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDoScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository
import com.jeanbarrossilva.self.wheel.inmemory.utils.replacingBy

internal class InMemoryToDoScope(
    private val repository: InMemoryWheelRepository,
    areaScope: AreaScope,
    private val wheel: Wheel,
    private val area: Area,
    private val toDo: ToDo
) : ToDoScope(areaScope, toDo) {
    override suspend fun onSubmission() {
        editToDo {
            copy(this@InMemoryToDoScope.title, this@InMemoryToDoScope.isDone)
        }
    }

    private fun editToDo(edit: ToDo.() -> ToDo) {
        editArea {
            val editedToDos = toDos.toMutableList().replacingBy(edit) { it.title == toDo.title }
            copy(toDos = editedToDos)
        }
    }

    private fun editArea(edit: Area.() -> Area) {
        editWheel {
            val editedAreas =
                areas.toMutableList().replacingBy(edit) { it.name == area.name }.toList()
            copy(areas = editedAreas)
        }
    }

    private fun editWheel(edit: Wheel.() -> Wheel) {
        with(repository.wheels) {
            value = value.toMutableList().replacingBy(edit) { it.name == wheel.name }.toList()
        }
    }
}
