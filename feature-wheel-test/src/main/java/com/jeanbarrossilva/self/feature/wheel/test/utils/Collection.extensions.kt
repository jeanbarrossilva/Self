package com.jeanbarrossilva.self.feature.wheel.test.utils

/** Creates a single [ClosedFloatingPointRange] based on the leading and trailing ones. **/
internal fun Collection<ClosedFloatingPointRange<Float>>.flatten():
    ClosedFloatingPointRange<Float> {
    return if (isNotEmpty()) {
        first().start..last().endInclusive
    } else {
        throw IllegalStateException("Cannot flatten an empty collection of ranges.")
    }
}
