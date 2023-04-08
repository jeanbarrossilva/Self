package com.jeanbarrossilva.self.feature.wheel.core.loadable

import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.map
import com.jeanbarrossilva.loadable.utils.serialize
import com.jeanbarrossilva.self.feature.wheel.utils.toLoadableList
import java.io.Serializable

internal inline fun <I : Serializable?, reified O : Serializable?> LoadableList<I>.flatMap(
    transform: (I) -> SerializableList<O>
): LoadableList<O> {
    return toLoadable().map { it.flatMap(transform).serialize() }.toLoadableList()
}
