package com.jeanbarrossilva.self.wheel.android.domain.wheel

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaScope
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.utils.domain
import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.flow.first

internal class AndroidWheelScope(
    editor: WheelEditor,
    private val wheelDao: AndroidWheelDao,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao,
    private val wheel: Wheel
) : WheelScope(editor, wheel) {
    override fun createAreaScope(area: Area): AndroidAreaScope {
        return AndroidAreaScope(this, areaDao, toDoDao, wheel, area)
    }

    override suspend fun onSubmission() {
        val id = wheelDao
            .selectByName(wheel.name)
            .first()
            .firstOrNull()
            ?.id
            ?: throw IllegalStateException("No wheel named \"${wheel.name}\" found.")
        wheelDao.setName(id, name)
        updateAndAddAreas()
    }

    private suspend fun updateAndAddAreas() {
        updatePreExistentAreas()
        addNewAreas()
    }

    private suspend fun updatePreExistentAreas() {
        wheel.areas.map { onArea(it.name) as AndroidAreaScope }.forEach {
            it.updateAndAddToDos()
        }
    }

    private suspend fun addNewAreas() {
        areas
            .filter { wheel.areas[it.name] == null }
            .map { it.domain(name) }
            .onEach { areaDao.insert(it) }
            .forEach { (onArea(it.name) as AndroidAreaScope).updateAndAddToDos() }
    }
}
