package com.jeanbarrossilva.self.feature.wheel.core.loadable

import com.jeanbarrossilva.loadable.list.SerializableList
import com.jeanbarrossilva.loadable.list.serialize
import com.jeanbarrossilva.loadable.map
import com.jeanbarrossilva.self.feature.wheel.utils.toLoadableList
import java.io.Serializable

internal inline fun <I : Serializable?, reified O : Serializable?> LoadableList<I>.flatMap(
    transform: (I) -> SerializableList<O>
): LoadableList<O> {
    return toLoadable()
        .map<SerializableList<I>, _> { it.flatMap(transform).serialize() }
        .toLoadableList()
}
