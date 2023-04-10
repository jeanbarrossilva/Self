package com.jeanbarrossilva.self.feature.questionnaire.step.unanswerable.type

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.step.StepPosition
import com.jeanbarrossilva.self.feature.questionnaire.step.unanswerable.UnanswerableStepFragment

internal class AnnouncementFragment : UnanswerableStepFragment {
    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener
    ) : super(position, onPreviousListener, onNextListener)

    @Composable
    @Suppress("SpellCheckingInspection")
    override fun Title() {
        Text("Bem-vindo ao Self!")
        Text("Antes de começarmos, preciso que responda às perguntas a seguir.")
        Text("Vamos lá?")
    }
}
