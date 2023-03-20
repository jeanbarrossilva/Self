package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister

class InMemoryWheelRegister(override val repository: InMemoryWheelRepository) : WheelRegister() {
    override suspend fun onRegister(wheel: Wheel) {
        repository.wheels.value += wheel
    }

    override suspend fun clear() {
        repository.wheels.value = emptyList()
    }
}
