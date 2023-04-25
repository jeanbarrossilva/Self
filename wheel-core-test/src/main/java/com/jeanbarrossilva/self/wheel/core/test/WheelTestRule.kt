package com.jeanbarrossilva.self.wheel.core.test

import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.rules.ExternalResource

abstract class WheelTestRule : ExternalResource() {
    abstract val repository: WheelRepository
    abstract val register: WheelRegister
    abstract val editor: WheelEditor

    @OptIn(ExperimentalCoroutinesApi::class)
    final override fun after() {
        runTest {
            register.clear()
            onAfter()
        }
    }

    protected open suspend fun onAfter() {
    }

    companion object
}
