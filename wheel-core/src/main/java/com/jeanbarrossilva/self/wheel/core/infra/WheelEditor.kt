package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.flow.first

abstract class WheelEditor {
    protected abstract val repository: WheelRepository

    suspend fun setName(wheelName: String, name: String) {
        assertExists(wheelName)
        onSetName(wheelName, name)
    }

    suspend fun addArea(wheelName: String, areaName: String, areaAttention: Float) {
        assertExists(wheelName)
        val area = Area(areaName, areaAttention)
        onAddArea(wheelName, area)
    }

    suspend fun addToDo(wheelName: String, areaName: String, toDoTitle: String) {
        assertExists(wheelName, areaName)
        val toDo = ToDo(toDoTitle, isDone = false)
        onAddToDo(wheelName, areaName, toDo)
    }

    abstract suspend fun toggleToDo(
        wheelName: String,
        areaName: String,
        toDoTitle: String,
        isToDoDone: Boolean
    )

    protected abstract suspend fun onSetName(wheelName: String, name: String)

    protected abstract suspend fun onAddArea(wheelName: String, area: Area)

    protected abstract suspend fun onAddToDo(wheelName: String, areaName: String, toDo: ToDo)

    private suspend fun assertExists(wheelName: String, areaName: String) {
        assertExists(wheelName)
        val isAreaExistent = getWheel(wheelName)?.get(areaName) != null
        assert(isAreaExistent)
    }

    private suspend fun assertExists(wheelName: String) {
        val isExistent = getWheel(wheelName) != null
        assert(isExistent)
    }

    private suspend fun getWheel(name: String): Wheel? {
        return repository.fetch().first()[name]
    }
}
