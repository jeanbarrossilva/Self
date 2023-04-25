package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class AndroidWheelRepository(
    wheelDao: AndroidWheelDao,
    areaDao: AndroidAreaDao,
    toDoDao: AndroidToDoDao
) : WheelRepository() {
    private val wheelsFlow = combine(
        wheelDao.selectAll(),
        areaDao.selectAll(),
        toDoDao.selectAll()
    ) { wheels, areas, toDos ->
        wheels.map { wheel ->
            wheel.core(
                areas = areas.filter { area -> area.parent == wheel.name },
                toDos = toDos.filter { toDo -> toDo.grandparent == wheel.name }
            )
        }
    }

    override fun fetch(): Flow<List<Wheel>> {
        return wheelsFlow
    }
}
