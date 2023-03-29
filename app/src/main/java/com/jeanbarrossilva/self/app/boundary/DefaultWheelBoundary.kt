package com.jeanbarrossilva.self.app.boundary

import androidx.navigation.NavController
import com.jeanbarrossilva.self.app.R
import com.jeanbarrossilva.self.feature.wheel.WheelBoundary

internal class DefaultWheelBoundary : WheelBoundary {
    override fun navigateToQuestionnaire(navController: NavController) {
        navController.navigate(R.id.to_questionnaire)
    }
}
