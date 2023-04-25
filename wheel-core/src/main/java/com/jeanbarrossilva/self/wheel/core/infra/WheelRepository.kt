package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import kotlinx.coroutines.flow.Flow

abstract class WheelRepository {
    abstract fun fetch(): Flow<List<Wheel>>
}
