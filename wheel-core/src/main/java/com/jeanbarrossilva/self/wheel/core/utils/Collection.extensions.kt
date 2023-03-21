package com.jeanbarrossilva.self.wheel.core.utils

import com.jeanbarrossilva.self.wheel.core.domain.Area
import com.jeanbarrossilva.self.wheel.core.domain.ToDo
import com.jeanbarrossilva.self.wheel.core.domain.Wheel

operator fun Collection<Area>.get(name: String): Area? {
    return find {
        it.name == name
    }
}

operator fun Collection<ToDo>.get(title: String): ToDo? {
    return find {
        it.title == title
    }
}

operator fun Collection<Wheel>.get(name: String): Wheel? {
    return find {
        it.name == name
    }
}
