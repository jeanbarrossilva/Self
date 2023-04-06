package com.jeanbarrossilva.self.feature.questionnaire

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jeanbarrossilva.self.feature.questionnaire.domain.attention.Attention
import com.jeanbarrossilva.self.wheel.core.infra.WheelEditor
import com.jeanbarrossilva.self.wheel.core.infra.WheelRegister
import kotlinx.coroutines.launch

internal class QuestionnaireViewModel(
    application: Application,
    private val register: WheelRegister,
    private val editor: WheelEditor
) : AndroidViewModel(application) {
    private val answers = mutableMapOf<String, @Attention Float>()

    fun answer(areaName: String, @Attention answer: Float?) {
        answer?.let {
            answers[areaName] = it
        }
    }

    fun registerWheel() {
        viewModelScope.launch {
            register.register(WHEEL_NAME)
            addAreas()
        }
    }

    private suspend fun addAreas() {
        answers.forEach { (areaName, answer) ->
            editor.addArea(WHEEL_NAME, areaName, areaAttention = answer)
        }
    }

    companion object {
        private const val WHEEL_NAME = "Roda da vida"

        fun createFactory(application: Application, register: WheelRegister, editor: WheelEditor):
            ViewModelProvider.Factory {
            return viewModelFactory {
                addInitializer(QuestionnaireViewModel::class) {
                    QuestionnaireViewModel(application, register, editor)
                }
            }
        }
    }
}
