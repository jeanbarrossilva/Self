package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class AndroidWheelRepository(
    private val wheelDao: AndroidWheelDao,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao,
    private val coroutineScope: CoroutineScope
) : WheelRepository() {
    override suspend fun fetch(): StateFlow<List<Wheel>> {
        return combine(
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
            .stateIn(coroutineScope)
    }
}
