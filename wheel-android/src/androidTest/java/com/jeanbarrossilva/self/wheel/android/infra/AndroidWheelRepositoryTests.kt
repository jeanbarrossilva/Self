package com.jeanbarrossilva.self.wheel.android.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.android.test.android
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class AndroidWheelRepositoryTests {
    @get:Rule
    val androidWheelRule = WheelTestRule.android()

    @Test
    fun fetches() {
        runTest {
            repeat(8) { androidWheelRule.register.register("${it.inc()}") }
            androidWheelRule.repository.fetch().test { assertEquals(8, awaitItem().size) }
        }
    }
}
