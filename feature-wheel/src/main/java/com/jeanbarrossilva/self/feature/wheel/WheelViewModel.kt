package com.jeanbarrossilva.self.feature.wheel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.flow.loadable
import com.jeanbarrossilva.loadable.flow.unwrap
import com.jeanbarrossilva.loadable.flow.unwrapContent
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.feature.wheel.utils.ignore
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

internal class WheelViewModel(
    repository: WheelRepository,
    private val editor: WheelEditor,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Main.immediate
) : ViewModel() {
    private val coroutineScope = viewModelScope + coroutineDispatcher
    private val nullableWheelLoadableFlow =
        repository.fetch().map { it.singleOrNull()?.feature() }.loadable()

    val wheelLoadableFlow = nullableWheelLoadableFlow.ignore(1).unwrapContent().stateIn(
        coroutineScope,
        SharingStarted.WhileSubscribed(),
        initialValue = Loadable.Loading()
    )

    fun doOnNonexistentWheel(block: () -> Unit) {
        coroutineScope.launch(coroutineDispatcher) {
            val isWheelNonexistent = nullableWheelLoadableFlow.unwrap().first() == null
            if (isWheelNonexistent) {
                block()
            }
        }
    }

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        coroutineScope.launch(coroutineDispatcher) {
            val wheel = wheelLoadableFlow.unwrap().first()
            editor.onWheel(wheel.name).onArea(area.name).onToDo(toDo.title).setDone(isDone).submit()
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
