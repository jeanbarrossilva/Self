package com.jeanbarrossilva.self.app.boundary

import androidx.navigation.NavController
import com.jeanbarrossilva.self.app.R
import com.jeanbarrossilva.self.feature.questionnaire.QuestionnaireBoundary

internal class DefaultQuestionnaireBoundary : QuestionnaireBoundary {
    override fun navigateToWheel(navController: NavController) {
        navController.popBackStack(R.id.wheel_fragment, inclusive = false)
    }
}
