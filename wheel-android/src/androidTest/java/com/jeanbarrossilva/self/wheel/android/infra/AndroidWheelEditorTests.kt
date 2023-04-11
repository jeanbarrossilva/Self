package com.jeanbarrossilva.self.wheel.android.infra

import com.jeanbarrossilva.self.wheel.android.test.AndroidWheelTestRule
import com.jeanbarrossilva.self.wheel.core.utils.get
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestScope
import org.junit.Assert.assertNotNull
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class AndroidWheelEditorTests {
    private val coroutineScope = TestScope()
    private val androidWheelRule = AndroidWheelTestRule(coroutineScope)

    @Test
    fun addsToDo() {
        val wheelName = "<3"
        val areaName = ":*"
        val toDoTitle = ":>"
        coroutineScope.launch {
            androidWheelRule.register.register(wheelName)
            androidWheelRule.editor.addArea(wheelName, areaName, areaAttention = .8f)
            androidWheelRule.editor.addToDo(wheelName, areaName, toDoTitle)
            assertNotNull(
                androidWheelRule
                    .repository
                    .fetch()
                    .value[wheelName]
                    ?.areas
                    ?.get(areaName)
                    ?.toDos
                    ?.get(toDoTitle)
            )
        }
    }
}
