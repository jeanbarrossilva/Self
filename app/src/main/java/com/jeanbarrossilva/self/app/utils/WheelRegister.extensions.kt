package com.jeanbarrossilva.self.app.utils

import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister

internal suspend fun WheelRegister.register(editor: WheelEditor, wheel: FeatureWheel) {
    register(wheel.name)
    wheel.areas.forEach { area ->
        editor.addArea(wheel.name, area.name, area.attention)
        area.toDos.forEach { toDo -> editor.addToDo(wheel.name, area.name, toDo.title) }
    }
}
