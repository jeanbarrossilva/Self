package com.jeanbarrossilva.self.wheel.inmemory.infra

import app.cash.turbine.test
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest

internal class InMemoryWheelRegisterTests {
    private val repository = InMemoryWheelRepository()
    private val register = InMemoryWheelRegister(repository)

    @AfterTest
    @OptIn(ExperimentalCoroutinesApi::class)
    fun tearDown() {
        runTest {
            register.clear()
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an area WHEN registering it THEN it's been added to the repository`() {
        runTest {
            register.register("Jonathan")
            repository.fetch().test { assertEquals("Jonathan", awaitItem().first().name) }
        }
    }
}
