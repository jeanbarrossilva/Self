package com.jeanbarrossilva.self.wheel.inmemory.utils

internal fun <E, L : MutableList<E>> L.replacingBy(
    replacement: E.() -> E,
    predicate: (E) -> Boolean
): L {
    return apply {
        replaceAll {
            if (predicate(it)) it.replacement() else it
        }
    }
}
