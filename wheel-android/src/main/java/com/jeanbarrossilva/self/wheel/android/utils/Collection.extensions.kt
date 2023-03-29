package com.jeanbarrossilva.self.wheel.android.utils

import com.jeanbarrossilva.self.wheel.android.domain.todo.AndroidToDoEntity
import com.jeanbarrossilva.self.wheel.android.domain.wheel.AndroidWheelEntity

internal operator fun Collection<AndroidToDoEntity>.get(title: String): AndroidToDoEntity? {
    return find {
        it.title == title
    }
}

internal operator fun Collection<AndroidWheelEntity>.get(name: String): AndroidWheelEntity? {
    return find {
        it.name == name
    }
}
