package com.jeanbarrossilva.self.feature.wheel.core.loadable

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.list.SerializableList
import com.jeanbarrossilva.loadable.list.emptySerializableList
import java.io.Serializable

internal sealed interface LoadableList<T : Serializable?> {
    class Loading<T : Serializable?> : LoadableList<T> {
        override fun toLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Loading()
        }
    }

    data class Populated<T : Serializable?>(val value: SerializableList<T>) : LoadableList<T> {
        init {
            assert(value.isNotEmpty()) {
                "LoadableList.Populated cannot hold an empty list; use LoadableList.Empty instead."
            }
        }

        override fun toLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Loaded(value)
        }
    }

    class Empty<T : Serializable?> : LoadableList<T> {
        override fun toLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Loaded(emptySerializableList())
        }
    }

    data class Failed<T : Serializable?>(val error: Throwable) : LoadableList<T> {
        override fun toLoadable(): Loadable<SerializableList<T>> {
            return Loadable.Failed(error)
        }
    }

    fun toLoadable(): Loadable<SerializableList<T>>
}
