package com.jeanbarrossilva.self.feature.questionnaire.scope.step.type

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepFragment
import com.jeanbarrossilva.self.feature.questionnaire.scope.step.StepPosition

internal class AnnouncementFragment : StepFragment {
    constructor() : super()

    constructor(
        position: StepPosition,
        onPreviousListener: OnPreviousListener,
        onNextListener: OnNextListener,
        onDoneListener: OnDoneListener
    ) : super(position, onPreviousListener, onNextListener, onDoneListener)

    @Composable
    @Suppress("SpellCheckingInspection")
    override fun Title() {
        Text("Bem-vindo ao Self!")
        Text("Antes de começarmos, preciso que responda às perguntas a seguir.")
        Text("Vamos lá?")
    }
}
