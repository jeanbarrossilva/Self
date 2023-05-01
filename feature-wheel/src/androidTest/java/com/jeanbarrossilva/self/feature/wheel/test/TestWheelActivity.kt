package com.jeanbarrossilva.self.feature.wheel.test

import androidx.navigation.NavGraphBuilder
import androidx.navigation.fragment.fragment
import com.jeanbarrossilva.self.feature.wheel.WheelFragment
import com.jeanbarrossilva.self.platform.ui.core.fragment.SingleDestinationActivity

internal class TestWheelActivity : SingleDestinationActivity() {
    override val route = "wheel"

    override fun NavGraphBuilder.add() {
        fragment<WheelFragment>(this@TestWheelActivity.route)
    }
}
