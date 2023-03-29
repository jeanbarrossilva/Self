package com.jeanbarrossilva.self.feature.questionnaire

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

@Suppress("FunctionName")
fun QuestionnaireModule(): Module {
    return module {
        viewModel {
            QuestionnaireViewModel(androidApplication(), register = get(), editor = get())
        }
    }
}
