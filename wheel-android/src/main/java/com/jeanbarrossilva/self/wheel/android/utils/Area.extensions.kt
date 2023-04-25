package com.jeanbarrossilva.self.wheel.android.utils

import com.jeanbarrossilva.self.wheel.android.domain.area.AndroidAreaEntity
import com.jeanbarrossilva.self.wheel.core.domain.area.Area

internal fun Area.domain(parent: String): AndroidAreaEntity {
    return AndroidAreaEntity(id = 0, parent, name, attention)
}
