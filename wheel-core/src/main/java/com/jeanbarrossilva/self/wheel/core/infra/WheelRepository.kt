package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import kotlinx.coroutines.flow.Flow

abstract class WheelRepository {
    abstract suspend fun fetch(): Flow<List<Wheel>>
}
