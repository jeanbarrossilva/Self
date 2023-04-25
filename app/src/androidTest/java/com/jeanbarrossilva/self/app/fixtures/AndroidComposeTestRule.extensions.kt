package com.jeanbarrossilva.self.app.fixtures

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.compose.ui.test.junit4.AndroidComposeTestRule
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.fragment.findNavController
import androidx.test.ext.junit.rules.ActivityScenarioRule
import kotlin.coroutines.resume
import kotlin.time.Duration
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull

/** Primary navigation [Fragment]'s [NavController]. **/
private inline val <T : FragmentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.navController // ktlint-disable max-line-length
    get() = activity
        .supportFragmentManager
        .primaryNavigationFragment
        ?.findNavController()
        ?: throw IllegalStateException("No primary navigation Fragment found.")

/** Signals that the ID does not belong to any of the [NavDestination]s in the [NavGraph]. **/
internal class NonexistentDestinationException(@IdRes id: Int) :
    IllegalArgumentException("No destination identified as $id was found in the graph.")

/** Signals that we have navigated to some [NavDestination] that's not the expected one. **/
internal class IllegalDestinationException(expected: NavDestination, actual: NavDestination) :
    IllegalStateException("Navigated to $actual instead of to $expected.")

/**
 * Suspends until [timeout], waiting for the [NavDestination] identified as [id] to get navigated
 * to.
 *
 * @param coroutineScope [CoroutineScope] in which navigation to the [NavDestination] will be
 * awaited.
 * @param id [ID][NavDestination.id] of the [NavDestination].
 * @throws NonexistentDestinationException If such [NavDestination] doesn't exist.
 * @throws IllegalStateException If the [AndroidComposeTestRule.activity] doesn't have a primary
 * navigation [Fragment].
 * @throws IllegalDestinationException If the [NavController] navigates to a [NavDestination] other
 * than the awaited one.
 * @throws CancellationException If the [NavController] doesn't navigate to the [NavDestination]
 * within the [timeout] timeframe.
 **/
@Suppress("KDocUnresolvedReference")
internal suspend inline fun <T : FragmentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.waitForDestination( // ktlint-disable max-line-length
    coroutineScope: CoroutineScope,
    @IdRes id: Int,
    timeout: Duration
) {
    val job = coroutineScope.launch { waitForDestination(id, isInclusive = false) }
    withTimeoutOrNull(timeout) { job.join() } ?: job.cancel(
        "Didn't navigate to ${getDestination(id)} after $timeout."
    )
}

/**
 * Suspends until the [NavDestination] identified as [id] gets navigated to.
 *
 * @param id [ID][NavDestination.id] of the [NavDestination].
 * @param isInclusive Whether the current [NavDestination] being the awaited one resumes the
 * coroutine instead of waiting for the next one to be checked against.
 * @throws NonexistentDestinationException If such [NavDestination] doesn't exist.
 * @throws IllegalStateException If the [AndroidComposeTestRule.activity] doesn't have a primary
 * navigation [Fragment].
 * @throws IllegalDestinationException If the [NavController] navigates to a [NavDestination] other
 * than the awaited one.
 **/
@Suppress("KDocUnresolvedReference")
internal suspend inline fun <T : FragmentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.waitForDestination( // ktlint-disable max-line-length
    @IdRes id: Int,
    isInclusive: Boolean = true
) {
    return suspendCancellableCoroutine { continuation ->
        val listener = object : NavController.OnDestinationChangedListener {
            var isAtCurrent = true

            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                if (isInclusive || !isAtCurrent) resumeOnMatch(controller, destination)
                if (isAtCurrent) isAtCurrent = false
            }

            private fun resumeOnMatch(controller: NavController, destination: NavDestination) {
                if (destination.id == id) {
                    continuation.resume(Unit)
                    controller.removeOnDestinationChangedListener(this)
                } else {
                    throw IllegalDestinationException(
                        expected = getDestination(id),
                        actual = destination
                    )
                }
            }
        }
        navController.addOnDestinationChangedListener(listener)
    }
}

/**
 * Gets the [NavDestination] that's identified as [id].
 *
 * @param [ID][NavDestination.id] of the [NavDestination].
 * @throws NonexistentDestinationException If such [NavDestination] doesn't exist.
 **/
@Suppress("KDocUnresolvedReference")
private fun <T : FragmentActivity> AndroidComposeTestRule<ActivityScenarioRule<T>, T>.getDestination( // ktlint-disable max-line-length
    @IdRes id: Int
): NavDestination {
    return navController.graph.findNode(id) ?: throw NonexistentDestinationException(id)
}
