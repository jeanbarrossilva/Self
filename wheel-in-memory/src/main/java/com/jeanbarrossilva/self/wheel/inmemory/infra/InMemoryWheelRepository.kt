package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryWheelRepository : WheelRepository() {
    internal val wheels = MutableStateFlow(emptyList<Wheel>())

    override suspend fun fetch(): StateFlow<List<Wheel>> {
        return wheels.asStateFlow()
    }
}
