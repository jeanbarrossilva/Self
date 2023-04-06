package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.utils.get

abstract class WheelRegister {
    protected abstract val repository: WheelRepository

    abstract suspend fun clear()

    suspend fun register(name: String) {
        assertDoestNotExist(name)
        val wheel = Wheel(name)
        onRegister(wheel)
    }

    protected abstract suspend fun onRegister(wheel: Wheel)

    private suspend fun assertDoestNotExist(name: String) {
        val isNonexistent = repository.fetch().value[name] == null
        assert(isNonexistent)
    }
}
