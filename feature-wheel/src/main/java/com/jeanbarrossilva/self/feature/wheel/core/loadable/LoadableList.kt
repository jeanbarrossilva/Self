package com.jeanbarrossilva.self.feature.wheel.core.loadable

import com.jeanbarrossilva.loadable.Loadable
import com.jeanbarrossilva.loadable.type.SerializableList
import com.jeanbarrossilva.loadable.utils.emptySerializableList
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
                "ListLoadable.Loaded cannot hold an empty list; use Empty instead."
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
