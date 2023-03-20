package com.jeanbarrossilva.self.wheel.core.utils

import com.jeanbarrossilva.self.wheel.core.domain.Wheel

internal fun Collection<Wheel>.filterNamed(name: String): List<Wheel> {
    return filter {
        it.name == name
    }
}
