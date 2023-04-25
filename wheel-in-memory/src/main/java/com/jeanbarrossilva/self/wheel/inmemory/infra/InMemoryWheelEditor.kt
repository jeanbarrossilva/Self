package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.domain.wheel.InMemoryWheelScope

class InMemoryWheelEditor(override val repository: InMemoryWheelRepository) : WheelEditor() {
    override fun createWheelScope(wheel: Wheel): WheelScope {
        return InMemoryWheelScope(repository, this, wheel)
    }
}
