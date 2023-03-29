package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal enum class StepPosition : Parcelable {
    LEADING,
    IN_BETWEEN,
    TRAILING;

    companion object {
        fun from(stepCount: Int, stepIndex: Int): StepPosition {
            return when (stepIndex) {
                0 -> LEADING
                stepCount -> TRAILING
                else -> IN_BETWEEN
            }
        }
    }
}
