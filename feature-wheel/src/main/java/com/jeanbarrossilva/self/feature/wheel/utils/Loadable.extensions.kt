package com.jeanbarrossilva.self.feature.wheel.utils

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.self.feature.wheel.core.loadable.LoadableList
import java.io.Serializable

internal fun <T : Serializable?> Loadable<SerializableList<T>>.toLoadableList(): LoadableList<T> {
    return when (this) {
        is Loadable.Loading -> LoadableList.Loading()
        is Loadable.Loaded ->
            if (value.isEmpty()) LoadableList.Empty() else LoadableList.Populated(value)
        is Loadable.Failed -> LoadableList.Failed(error)
    }
}
