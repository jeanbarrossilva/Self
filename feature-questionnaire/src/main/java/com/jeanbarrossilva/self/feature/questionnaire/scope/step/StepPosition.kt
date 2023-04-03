package com.jeanbarrossilva.self.feature.questionnaire.scope.step

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal enum class StepPosition : Parcelable {
    LEADING,
    IN_BETWEEN,
    TRAILING
}
