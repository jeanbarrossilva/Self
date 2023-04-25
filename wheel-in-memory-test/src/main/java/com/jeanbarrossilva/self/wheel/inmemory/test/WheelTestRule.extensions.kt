package com.jeanbarrossilva.self.wheel.inmemory.test

import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRegister
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository

fun WheelTestRule.Companion.inMemory(): WheelTestRule {
    return object : WheelTestRule() {
        override val repository = InMemoryWheelRepository()
        override val register = InMemoryWheelRegister(repository)
        override val editor = InMemoryWheelEditor(repository)
    }
}
