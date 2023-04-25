package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaDao
import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelDao
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository

class AndroidWheelEditor(
    override val repository: WheelRepository,
    private val wheelDao: AndroidWheelDao,
    private val areaDao: AndroidAreaDao,
    private val toDoDao: AndroidToDoDao
) : WheelEditor() {
    override fun createWheelScope(wheel: Wheel): WheelScope {
        return AndroidWheelScope(this, wheelDao, areaDao, toDoDao, wheel)
    }
}
