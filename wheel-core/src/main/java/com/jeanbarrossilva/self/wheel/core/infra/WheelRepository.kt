package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import kotlinx.coroutines.flow.StateFlow

abstract class WheelRepository {
    abstract suspend fun fetch(): StateFlow<List<Wheel>>
}
