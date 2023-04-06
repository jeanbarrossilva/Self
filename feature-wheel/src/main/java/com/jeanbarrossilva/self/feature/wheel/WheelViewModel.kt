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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch

internal class WheelViewModel(
    application: Application,
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    private val wheelLoadableFlow = MutableStateFlow<Loadable<FeatureWheel?>>(Loadable.Loading())
        .apply {
            viewModelScope.launch {
                repository
                    .fetch()
                    .map { it.firstOrNull()?.feature() }
                    .loadable()
                    .ignore(1)
                    .collect { emit(it) }
            }
        }

    private val wheel
        get() = wheelLoadableFlow.value.valueOrNull

    fun getWheelLoadableFlow(): StateFlow<Loadable<FeatureWheel>> {
        @Suppress("UNCHECKED_CAST")
        return wheelLoadableFlow
            .filterNotNull() // Why not return StateFlow<Loadable<FeatureWheel>>? 🥲
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(),
                initialValue = Loadable.Loading()
            ) as StateFlow<Loadable<FeatureWheel>>
    }

    fun doOnNonexistentWheel(block: () -> Unit) {
        viewModelScope.launch {
            wheelLoadableFlow.filterIsLoaded().take(1).collect {
                if (wheel == null) {
                    block()
                }
            }
        }
    }

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            wheelLoadableFlow.value.valueOrNull?.let {
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
