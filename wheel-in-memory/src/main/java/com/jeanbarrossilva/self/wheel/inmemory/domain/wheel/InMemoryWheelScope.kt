package com.jeanbarrossilva.self.wheel.inmemory.domain.wheel

import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel
import com.jeanbarrossilva.self.wheel.core.domain.wheel.WheelScope
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.inmemory.domain.area.InMemoryAreaScope
import com.jeanbarrossilva.self.wheel.inmemory.infra.InMemoryWheelRepository
import com.jeanbarrossilva.self.wheel.inmemory.utils.replacingBy

internal class InMemoryWheelScope(
    private val repository: InMemoryWheelRepository,
    editor: WheelEditor,
    private val wheel: Wheel
) : WheelScope(editor, wheel) {
    override suspend fun onSubmission() {
        editWheel {
            copy(this@InMemoryWheelScope.name, this@InMemoryWheelScope.areas)
        }
    }

    override fun createAreaScope(area: Area): AreaScope {
        return InMemoryAreaScope(repository, this, wheel, area)
    }

    private fun editWheel(edit: Wheel.() -> Wheel) {
        with(repository.wheels) {
            value = value.toMutableList().replacingBy(edit) { it.name == wheel.name }.toList()
        }
    }
}
