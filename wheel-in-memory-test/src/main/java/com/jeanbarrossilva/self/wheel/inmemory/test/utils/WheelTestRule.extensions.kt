package com.jeanbarrossilva.self.wheel.inmemory.test.utils

import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRegister
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository

fun WheelTestRule.Companion.inMemory(): WheelTestRule {
    val repository = InMemoryWheelRepository()
    val register = InMemoryWheelRegister(repository)
    val editor = InMemoryWheelEditor(repository)
    return WheelTestRule(repository, register, editor)
}
