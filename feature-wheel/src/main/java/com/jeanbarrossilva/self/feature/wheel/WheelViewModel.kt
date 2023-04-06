package com.jeanbarrossilva.self.feature.wheel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.utils.filterIsLoaded
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.loadable.utils.valueOrNull
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.withIndex
import kotlinx.coroutines.launch

internal class WheelViewModel(
    application: Application,
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    private val wheel
        get() = wheelFlow.value.valueOrNull

    val wheelFlow = loadable(null) {
        repository
            .fetch()
            .map { it.singleOrNull()?.feature() }
            .loadable()
            .onEach { Log.d("WheelViewModel", "wheelFlow: $it") }
    }

    fun doOnNonexistentWheel(block: () -> Unit) {
        viewModelScope.launch {
            wheelFlow
                .filterIsLoaded()
                .withIndex()
                .filter { (index, _) -> index > 0 }
                .map { it.value }
                .collect {
                    if (it.value == null) {
                        block()
                    }
                }
        }
    }

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            wheel?.let {
                editor.toggleToDo(it.name, area.name, toDo.title, isDone)
            }
        }
    }

    companion object {
        fun createFactory(
            application: Application,
            repository: WheelRepository,
            editor: WheelEditor
        ): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(WheelViewModel::class) {
                    WheelViewModel(application, repository, editor)
                }
            }
        }
    }
}
