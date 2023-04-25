package com.jeanbarrossilva.self.wheel.inmemory.domain.area

import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDoScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.inmemory.domain.todo.InMemoryToDoScope
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository
import com.jeanbarrossilva.self.wheel.inmemory.utils.replacingBy

internal class InMemoryAreaScope(
    private val repository: InMemoryWheelRepository,
    wheelScope: WheelScope,
    private val wheel: Wheel,
    private val area: Area
) : AreaScope(wheelScope, area) {
    override fun createToDoScope(toDo: ToDo): ToDoScope {
        return InMemoryToDoScope(repository, this, wheel, area, toDo)
    }

    override suspend fun onSubmission() {
        editArea {
            copy(
                this@InMemoryAreaScope.name,
                this@InMemoryAreaScope.attention,
                this@InMemoryAreaScope.toDos
            )
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
