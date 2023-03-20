package com.jeanbarrossilva.self.wheel.core.infra

import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.utils.filterNamed
import kotlinx.coroutines.flow.first

abstract class WheelEditor {
    protected abstract val repository: WheelRepository

    suspend fun setName(wheelName: String, name: String) {
        assertExists(wheelName)
        onSetName(wheelName, name)
    }

    suspend fun addArea(wheelName: String, areaName: String, areaAttention: Float) {
        assertExists(wheelName)
        val area = Area(areaName, areaAttention)
        onAddArea(wheelName, area)
    }

    protected abstract suspend fun onSetName(wheelName: String, name: String)

    protected abstract suspend fun onAddArea(wheelName: String, area: Area)

    private suspend fun assertExists(wheelName: String) {
        val isExistent = repository.fetch().first().filterNamed(wheelName).isNotEmpty()
        assert(isExistent)
    }
}
