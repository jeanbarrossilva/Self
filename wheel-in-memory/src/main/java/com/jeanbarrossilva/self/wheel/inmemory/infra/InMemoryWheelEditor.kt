package com.jeanbarrossilva.self.wheel.inmemory.infra

import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.utils.replacingBy

class InMemoryWheelEditor(override val repository: InMemoryWheelRepository) : WheelEditor() {
    override suspend fun onSetName(wheelName: String, name: String) {
        edit(wheelName) {
            copy(name = name)
        }
    }

    override suspend fun onAddArea(wheelName: String, area: Area) {
        edit(wheelName) {
            copy(areas = areas + area)
        }
    }

    private fun edit(wheelName: String, edit: Wheel.() -> Wheel) {
        with(repository.wheels) {
            value = value.toMutableList().replacingBy(edit) { it.name == wheelName }.toList()
        }
    }
}
