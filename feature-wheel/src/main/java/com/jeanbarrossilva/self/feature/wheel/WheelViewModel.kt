package com.jeanbarrossilva.self.feature.wheel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.utils.filterIsLoaded
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.loadable.utils.valueOrNull
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureWheel
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.feature.wheel.utils.ignore
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class WheelViewModel(
    application: Application,
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    private val wheel
        get() = wheelFlow.value.valueOrNull

    val wheelFlow = MutableStateFlow<Loadable<FeatureWheel?>>(Loadable.Loading()).apply {
        viewModelScope.launch {
            repository
                .fetch()
                .map { it.firstOrNull()?.feature() }
                .loadable()
                .ignore(1)
                .collect { emit(it) }
        }
    }

    fun doOnNonexistentWheel(block: () -> Unit) {
        viewModelScope.launch {
            wheelFlow.filterIsLoaded().collect {
                if (wheel == null) {
                    block()
                }
            }
        }
    }

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            wheelFlow.value.valueOrNull?.let {
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
