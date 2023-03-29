package com.jeanbarrossilva.self.feature.questionnaire.scope.step.type

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.Swiper

internal class AnnouncementFragment(
    override val swiper: Swiper,
    override val position: StepPosition,
    private val onDone: () -> Unit
) : StepFragment() {
    @Composable
    @Suppress("SpellCheckingInspection")
    override fun Title() {
        Text("Bem-vindo ao Self!")
        Text("Antes de começarmos, preciso que responda às perguntas a seguir.")
        Text("Vamos lá?")
    }

    override fun onDone() {
        onDone.invoke()
    }
}
