package com.jeanbarrossilva.self.feature.wheel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.loadable.utils.loadable
import com.jeanbarrossilva.loadable.utils.unwrap
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureToDo
import com.jeanbarrossilva.self.feature.wheel.utils.feature
import com.jeanbarrossilva.self.platform.ui.utils.preferences
import com.jeanbarrossilva.self.wheel.core.domain.Wheel
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRepository
import kotlin.collections.firstOrNull
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class WheelViewModel(
    application: Application,
    private val repository: WheelRepository,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    val wheelFlow = flow { emitAll(repository.fetch()) }
        .map(List<Wheel>::firstOrNull)
        .map { it?.feature() }
        .loadable()

    val isQuestionnaireAnswered
        get() = getApplication<Application>()
            .preferences
            ?.getBoolean(IS_QUESTIONNAIRE_ANSWERED_KEY, false) == true

    fun toggleToDo(area: FeatureArea, toDo: FeatureToDo, isDone: Boolean) {
        viewModelScope.launch {
            wheelFlow.unwrap().first()?.let { wheel ->
                editor.toggleToDo(wheel.name, area.name, toDo.title, isDone)
            }
        }
    }

    companion object {
        private const val IS_QUESTIONNAIRE_ANSWERED_KEY = "is_questionnaire_answered"

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
