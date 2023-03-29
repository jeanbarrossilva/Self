package com.jeanbarrossilva.self.app.module

import com.jeanbarrossilva.self.app.boundary.DefaultQuestionnaireBoundary
import com.jeanbarrossilva.self.app.boundary.DefaultWheelBoundary
import com.jeanbarrossilva.self.feature.questionnaire.QuestionnaireBoundary
import com.jeanbarrossilva.self.feature.wheel.WheelBoundary
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
internal fun BoundaryModule(): Module {
    return module {
        single<QuestionnaireBoundary> { DefaultQuestionnaireBoundary() }
        single<WheelBoundary> { DefaultWheelBoundary() }
    }
}
