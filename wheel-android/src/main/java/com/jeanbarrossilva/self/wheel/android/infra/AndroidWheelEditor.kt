package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.android.utils.domain
import com.jeanbarrossilva.self.wheel.android.utils.get
import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.ToDo
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.first

class AndroidWheelEditor(
    override val repository: WheelRepository,
    private val wheelDao: AndroidWheelDao,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao
) : WheelEditor() {
    override suspend fun onSetName(wheelName: String, name: String) {
        val id = wheelDao.selectByName(wheelName).first()[name]!!.id
        wheelDao.setName(id, name)
    }

    override suspend fun onAddArea(wheelName: String, area: Area) {
        val entity = area.domain(parent = wheelName)
        val toDoEntities =
            area.toDos.map { it.domain(grandparent = wheelName, parent = entity.name) }
        areaDao.insert(entity)
        toDoDao.insert(toDoEntities)
    }

    override suspend fun onAddToDo(wheelName: String, areaName: String, toDo: ToDo) {
        val entity = toDo.domain(grandparent = wheelName, parent = areaName)
        toDoDao.insert(entity)
    }

    override suspend fun toggleToDo(
        wheelName: String,
        areaName: String,
        toDoTitle: String,
        isToDoDone: Boolean
    ) {
        val id = toDoDao
            .selectByRelationship(grandparent = wheelName, parent = areaName)
            .first()[toDoTitle]!!
            .id
        toDoDao.setDone(id, isToDoDone)
    }
}
