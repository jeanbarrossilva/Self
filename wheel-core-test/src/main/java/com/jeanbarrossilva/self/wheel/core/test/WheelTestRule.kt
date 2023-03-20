package com.jeanbarrossilva.self.wheel.core.test

import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.rules.ExternalResource

class WheelTestRule(
    val repository: WheelRepository,
    val register: WheelRegister,
    val editor: WheelEditor
) : ExternalResource() {
    @OptIn(ExperimentalCoroutinesApi::class)
    override fun after() {
        runTest {
            register.clear()
        }
    }

    companion object
}
