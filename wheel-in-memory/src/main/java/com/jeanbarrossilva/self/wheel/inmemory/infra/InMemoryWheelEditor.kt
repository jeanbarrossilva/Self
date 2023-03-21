package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.utils.replacingBy

class InMemoryWheelEditor(override val repository: InMemoryWheelRepository) : WheelEditor() {
    override suspend fun onSetName(wheelName: String, name: String) {
        edit(wheelName) {
            copy(name = name)
        }
    }

    override suspend fun onAddArea(wheelName: String, area: Area) {
        edit(wheelName) {
            copy(areas = areas + area)
        }
    }

    override suspend fun onAddToDo(wheelName: String, areaName: String, toDo: ToDo) {
        edit(wheelName, areaName) {
            copy(toDos = toDos + toDo)
        }
    }

    override suspend fun toggleToDo(
        wheelName: String,
        areaName: String,
        toDoTitle: String,
        isToDoDone: Boolean
    ) {
        edit(wheelName, areaName, toDoTitle) {
            copy(isDone = isToDoDone)
        }
    }

    private fun edit(
        wheelName: String,
        areaName: String,
        toDoTitle: String,
        edit: ToDo.() -> ToDo
    ) {
        edit(wheelName, areaName) {
            val editedToDos = toDos.toMutableList().replacingBy(edit) { it.title == toDoTitle }
            copy(toDos = editedToDos)
        }
    }

    private fun edit(wheelName: String, areaName: String, edit: Area.() -> Area) {
        edit(wheelName) {
            val editedAreas = areas.toMutableList().replacingBy(edit) { it.name == areaName }
            copy(areas = editedAreas)
        }
    }

    private fun edit(wheelName: String, edit: Wheel.() -> Wheel) {
        with(repository.wheels) {
            value = value.toMutableList().replacingBy(edit) { it.name == wheelName }.toList()
        }
    }
}
