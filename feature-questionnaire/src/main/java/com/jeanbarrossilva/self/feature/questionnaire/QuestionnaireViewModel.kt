package com.jeanbarrossilva.self.feature.questionnaire

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class QuestionnaireViewModel internal constructor(
    application: Application,
    private val register: WheelRegister,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    private val answers = mutableMapOf<String, @Attention Float>()

    internal fun answer(areaName: String, @Attention answer: Float?) {
        answer?.let {
            answers[areaName] = it
        }
    }

    internal fun registerWheel(): Job {
        return viewModelScope.launch {
            register.register(WHEEL_NAME)
            addAreas()
        }
    }

    private suspend fun addAreas() {
        val wheelScope = editor.onWheel(WHEEL_NAME)
        answers.forEach { (areaName, answer) -> wheelScope.addArea(areaName, answer) }
        wheelScope.submit()
    }

    companion object {
        const val WHEEL_NAME = "Roda da vida"

        internal fun createFactory(
            application: Application,
            register: WheelRegister,
            editor: WheelEditor
        ): ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(QuestionnaireViewModel::class) {
                    QuestionnaireViewModel(application, register, editor)
                }
            }
        }
    }
}
