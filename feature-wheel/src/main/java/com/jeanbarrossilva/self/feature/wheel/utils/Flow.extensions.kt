package com.jeanbarrossilva.self.feature.wheel.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.withIndex

/**
 * Ignores the first [count] elements.
 *
 * @param count Quantity of initial elements to be ignored. Ideally it'd be greater than zero, since
 * setting it as such simply wouldn't do anything.
 * @throws IllegalArgumentException When [count] is negative.
 **/
internal fun <T> Flow<T>.ignore(count: Int): Flow<T> {
    require(count >= 0) { "Count should be positive." }
    return withIndex().filter { it.index >= count }.map { it.value }
}
