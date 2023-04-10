package com.jeanbarrossilva.self.feature.questionnaire.step

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal enum class StepPosition : Parcelable {
    LEADING,
    IN_BETWEEN,
    TRAILING
}
