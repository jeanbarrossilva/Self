package com.jeanbarrossilva.self.feature.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.flow.loadableFlow
import com.jeanbarrossilva.loadable.flow.unwrap
import com.jeanbarrossilva.loadable.flow.unwrapContent
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

internal class WheelViewModel(
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : ViewModel() {
    private val wheelLoadableFlow = loadableFlow(viewModelScope) {
        repository.fetch().map { it.singleOrNull()?.feature() }.collect(::load)
    }

    fun getWheelLoadableFlow(): StateFlow<Loadable<FeatureWheel>> {
        return wheelLoadableFlow.unwrapContent().stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            initialValue = Loadable.Loading()
        )
    }

    fun doOnNonexistentWheel(block: () -> Unit): Job {
        return viewModelScope.launch {
            wheelLoadableFlow.unwrap().take(1).collect { wheel ->
                if (wheel == null) {
                    block()
                }
            }
        }
    }

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            val wheel = getWheelLoadableFlow().unwrap().first()
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
