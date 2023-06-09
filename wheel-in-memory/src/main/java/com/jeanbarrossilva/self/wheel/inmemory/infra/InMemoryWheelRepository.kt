package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryWheelRepository : WheelRepository() {
    internal val wheels = MutableStateFlow(emptyList<Wheel>())

    override fun fetch(): Flow<List<Wheel>> {
        return wheels.asStateFlow()
    }
}
