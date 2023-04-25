package com.jeanbarrossilva.self.wheel.android.utils

import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelEntity
import com.jeanbarrossilva.self.wheel.core.domain.wheel.Wheel

internal fun Wheel.domain(): AndroidWheelEntity {
    return AndroidWheelEntity(id = 0, name)
}
