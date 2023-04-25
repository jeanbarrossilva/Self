package com.jeanbarrossilva.self.wheel.core.domain.wheel

import com.jeanbarrossilva.self.wheel.core.domain.area.Area
import com.jeanbarrossilva.self.wheel.core.domain.area.AreaScope
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.utils.get

abstract class WheelScope(private val editor: WheelEditor, private val wheel: Wheel) {
    protected var name = wheel.name
        private set
    protected var areas = wheel.areas
        private set

    fun setName(name: String): WheelScope {
        return apply {
            this.name = name
        }
    }

    fun addArea(name: String, attention: Float): WheelScope {
        return apply {
            areas = areas + Area(name, attention)
        }
    }

    suspend fun onArea(areaName: String): AreaScope {
        val area = recreate().areas[areaName]
        return area?.let(::createAreaScope) ?: throw IllegalArgumentException(
            "Cannot edit a nonexistent \"$areaName\" area in the \"${wheel.name}\" wheel."
        )
    }

    suspend fun submit(): WheelScope {
        onSubmission()
        return recreate()
    }

    protected abstract fun createAreaScope(area: Area): AreaScope

    protected abstract suspend fun onSubmission()

    private suspend fun recreate(): WheelScope {
        return editor.onWheel(name)
    }
}
