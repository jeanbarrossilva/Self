package com.jeanbarrossilva.self.wheel.inmemory.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.core.domain.ToDo
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.core.utils.get
import com.jeanbarrossilva.self.wheel.inmemory.test.utils.inMemory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Rule

internal class InMemoryWheelEditorTests {
    @get:Rule
    val wheelRule = WheelTestRule.inMemory()

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a wheel's name WHEN editing it THEN it's changed in the repository`() {
        runTest {
            wheelRule.register.register("Joan")
            wheelRule.editor.setName(wheelName = "Joan", name = "Jonas")
            wheelRule.repository.fetch().test { assertEquals("Jonas", awaitItem().first().name) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an area WHEN adding it to a wheel THEN it's added in the repository`() {
        runTest {
            wheelRule.register.register("Jean")
            wheelRule.editor.addArea(wheelName = "Jean", areaName = "Work", areaAttention = .8f)
            wheelRule.repository.fetch().map { it.first().areas.first() }.test {
                awaitItem().let { area ->
                    assertEquals("Work", area.name)
                    assertEquals(.8f, area.attention)
                }
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a to-do WHEN adding it to an area THEN it's added in the repository`() {
        val toDoTitle = "Take Julia shopping"
        runTest {
            wheelRule.register.register("Danilo")
            wheelRule.editor.addArea(wheelName = "Danilo", areaName = "Family", areaAttention = .2f)
            wheelRule.editor.addToDo(wheelName = "Danilo", areaName = "Family", toDoTitle)
            getToDoFlow(wheelName = "Danilo", areaName = "Family", toDoTitle).test {
                assertEquals(toDoTitle, awaitItem().title)
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a to-do WHEN toggling it THEN the change is reflected on the repository`() {
        runTest {
            wheelRule.register.register("Alan")
            wheelRule.editor.addArea(wheelName = "Alan", areaName = "Studies", areaAttention = 1f)
            wheelRule.editor.addToDo(wheelName = "Alan", areaName = "Studies", toDoTitle = "Study")
            wheelRule.editor.toggleToDo(
                wheelName = "Alan",
                areaName = "Studies",
                toDoTitle = "Study",
                isToDoDone = true
            )
            getToDoFlow(wheelName = "Alan", areaName = "Studies", toDoTitle = "Study").test {
                assertTrue(awaitItem().isDone)
            }
        }
    }

    private suspend fun getToDoFlow(wheelName: String, areaName: String, toDoTitle: String):
        Flow<ToDo> {
        return wheelRule.repository.fetch().mapNotNull {
            it[wheelName]?.areas?.get(areaName)?.toDos?.get(toDoTitle)
        }
    }
}
