package com.jeanbarrossilva.self.feature.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.loadable.utils.unwrap
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class WheelViewModel(
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : ViewModel() {
    val wheelFlow = flow { emitAll(repository.fetch()) }.map { it.first().feature() }.loadable()

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            val wheel = wheelFlow.unwrap().first()
            editor.toggleToDo(wheel.name, area.name, toDo.title, isDone)
        }
    }

    companion object {
        fun createFactory(repository: WheelRepository, editor: WheelEditor):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(WheelViewModel::class) {
                    WheelViewModel(repository, editor)
                }
            }
        }
    }
}
