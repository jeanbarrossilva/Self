package com.jeanbarrossilva.self.platform.ui.core.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraphBuilder
import androidx.navigation.createGraph
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.jeanbarrossilva.self.platform.ui.R
import com.jeanbarrossilva.self.platform.ui.databinding.ActivitySingleFragmentBinding

/** [FragmentActivity] that contains a single [NavDestination]. **/
abstract class SingleDestinationActivity : FragmentActivity() {
    /** [ViewBinding] that gets assigned to at [onCreate] and nullified at [onDestroy]. **/
    private var binding: ActivitySingleFragmentBinding? = null

    /** [Route][NavDestination.route] of the [NavDestination]. **/
    protected abstract val route: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySingleFragmentBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        attachFragment()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    /** Callback that's run when the [NavGraph] is being built, on its [NavGraphBuilder]. **/
    protected abstract fun NavGraphBuilder.add()

    /** Attaches the [Fragment]. **/
    private fun attachFragment() {
        val navController = supportFragmentManager
            .findFragmentById(R.id.single_fragment_container_view)
            .let { it as NavHostFragment }
            .navController
        val graph = navController.createGraph(startDestination = route) { add() }
        assertThatHasSingleDestination(graph)
        navController.setGraph(graph, startDestinationArgs = null)
    }

    /**
     * Asserts that the [graph] has exactly one [NavDestination].
     *
     * @throws IllegalStateException If it has a number of [NavDestination]s other than 1.
     **/
    private fun assertThatHasSingleDestination(graph: NavGraph) {
        @Suppress("ReplaceSizeZeroCheckWithIsEmpty")
        if (graph.count() == 0) {
            throw IllegalStateException("SingleDestinationActivity should have a destination.")
        } else if (graph.count() > 1) {
            throw IllegalStateException(
                "SingleDestinationActivity should have only one destination."
            )
        }
    }
}
