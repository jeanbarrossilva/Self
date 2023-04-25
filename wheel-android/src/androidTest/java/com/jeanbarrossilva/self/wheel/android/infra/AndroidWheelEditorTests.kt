package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.test.android
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class AndroidWheelEditorTests {
    @get:Rule
    val androidWheelRule = WheelTestRule.android()

    @Test
    fun addsArea() {
        val wheelName = ";?"
        val areaName = ":8"
        runTest {
            androidWheelRule.register.register(wheelName)
            androidWheelRule.editor.onWheel(wheelName).addArea(areaName, .1f).submit()
            assertEquals(
                areaName,
                androidWheelRule.repository.fetch().first()[wheelName]?.get(areaName)?.name
            )
        }
    }

    @Test
    fun changesAreaAttention() {
        val wheelName = "<:>"
        val areaName = ":y"
        runTest {
            androidWheelRule.register.register(wheelName)
            androidWheelRule
                .editor
                .onWheel(wheelName)
                .addArea(areaName, .6f)
                .submit()
                .onArea(areaName)
                .setAttention(.5f)
                .submit()
            assertEquals(
                .5f,
                androidWheelRule.repository.fetch().first()[wheelName]?.get(areaName)?.attention
            )
        }
    }

    @Test
    fun addsToDo() {
        val wheelName = "<3"
        val areaName = ":*"
        val toDoTitle = ":>"
        runTest {
            androidWheelRule.register.register(wheelName)
            androidWheelRule
                .editor
                .onWheel(wheelName)
                .addArea(areaName, attention = .8f)
                .submit()
                .onArea(areaName)
                .addToDo(toDoTitle)
                .submit()
            assertNotNull(
                androidWheelRule
                    .repository
                    .fetch()
                    .first()[wheelName]
                    ?.areas
                    ?.get(areaName)
                    ?.toDos
                    ?.get(toDoTitle)
            )
        }
    }
}
