package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.flow.first

abstract class WheelEditor {
    protected abstract val repository: WheelRepository

    suspend fun onWheel(wheelName: String): WheelScope {
        val wheel = repository
            .fetch()
            .first()[wheelName]
            ?: throw IllegalArgumentException("Cannot edit a nonexistent \"$wheelName\" wheel.")
        return createWheelScope(wheel)
    }

    protected abstract fun createWheelScope(wheel: Wheel): WheelScope
}
