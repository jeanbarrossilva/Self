package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.android.utils.domain
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository

class AndroidWheelRegister(
    override val repository: WheelRepository,
    private val wheelDao: AndroidWheelDao,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao
) : WheelRegister() {
    override suspend fun onRegister(wheel: Wheel) {
        val wheelEntity = wheel.domain()
        val areaEntities = wheel.areas.map { it.domain(parent = wheelEntity.name) }
        val toDos = wheel.areas.flatMap { area ->
            area.toDos.map {
                it.domain(grandparent = wheelEntity.name, parent = area.name)
            }
        }
        wheelDao.insert(wheelEntity)
        areaDao.insert(areaEntities)
        toDoDao.insert(toDos)
    }

    override suspend fun clear() {
        toDoDao.deleteAll()
        areaDao.deleteAll()
        wheelDao.deleteAll()
    }
}
