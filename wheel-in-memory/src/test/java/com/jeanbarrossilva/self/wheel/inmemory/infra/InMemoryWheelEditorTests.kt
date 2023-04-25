package com.jeanbarrossilva.self.wheel.inmemory.infra

import app.cash.turbine.test
import com.jeanbarrossilva.self.wheel.core.domain.todo.ToDo
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
            wheelRule.editor.onWheel("Joan").setName("Jonas").submit()
            wheelRule.repository.fetch().test { assertEquals("Jonas", awaitItem().first().name) }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN an area WHEN adding it to a wheel THEN it's added in the repository`() {
        runTest {
            wheelRule.register.register("Jean")
            wheelRule.editor.onWheel("Jean").addArea(name = "Work", attention = .8f).submit()
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
        runTest {
            wheelRule.register.register("Danilo")
            wheelRule
                .editor
                .onWheel("Danilo")
                .addArea(name = "Family", attention = .2f)
                .submit()
                .onArea("Family")
                .addToDo("Take Julia shopping")
                .submit()
            getToDoFlow("Danilo", "Family", "Take Julia shopping").test {
                assertEquals("Take Julia shopping", awaitItem().title)
            }
        }
    }

    @Test
    @OptIn(ExperimentalCoroutinesApi::class)
    fun `GIVEN a to-do WHEN toggling it THEN the change is reflected on the repository`() {
        runTest {
            wheelRule.register.register("Alan")
            wheelRule
                .editor
                .onWheel("Alan")
                .addArea(name = "Studies", attention = 1f)
                .submit()
                .onArea("Studies")
                .addToDo("Study")
                .submit()
                .onToDo("Study")
                .setDone(true)
                .submit()
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
