package com.jeanbarrossilva.self.platform.ui.core.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Effect that attaches the given [observer] to the current [Lifecycle].
 *
 * @param event [Lifecycle.Event] that'll trigger the [observer].
 * @param observer Callback to be run whenever the current [Lifecycle.Event] is the specified one.
 **/
@Composable
fun LifecycleEventEffect(event: Lifecycle.Event, observer: (owner: LifecycleOwner) -> Unit) {
    LifecycleEventEffect { owner, _event ->
        if (_event == event) {
            observer(owner)
        }
    }
}

/**
 * Effect that attaches the given [observer] to the current [Lifecycle].
 *
 * @param observer [LifecycleEventObserver] to get notified of the current [Lifecycle]'s
 * [Lifecycle.Event] changes.
 **/
@Composable
fun LifecycleEventEffect(observer: LifecycleEventObserver) {
    LifecycleEventEffect(LocalLifecycleOwner.current.lifecycle, observer)
}

/**
 * Effect that attaches the given [observer] to [lifecycle].
 *
 * @param lifecycle [Lifecycle] to which [observer] will be attached.
 * @param observer [LifecycleEventObserver] to get notified of [lifecycle]'s [Lifecycle.Event]
 * changes.
 **/
@Composable
internal fun LifecycleEventEffect(lifecycle: Lifecycle, observer: LifecycleEventObserver) {
    var currentEvent by remember { mutableStateOf(Lifecycle.Event.ON_ANY) }
    val currentEventChangeObserver = remember {
        LifecycleEventObserver { _, e ->
            currentEvent = e
        }
    }

    DisposableEffect(lifecycle) {
        lifecycle.addObserver(currentEventChangeObserver)
        lifecycle.addObserver(observer)

        onDispose {
            lifecycle.removeObserver(observer)
            lifecycle.removeObserver(currentEventChangeObserver)
        }
    }
}
