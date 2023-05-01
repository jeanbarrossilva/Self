package com.jeanbarrossilva.self.feature.wheel

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.jeanbarrossilva.self.feature.wheel.test.TestWheelActivity
import com.jeanbarrossilva.self.feature.wheel.test.TestWheelBoundary
import com.jeanbarrossilva.self.feature.wheel.test.assertion.hasAreas
import com.jeanbarrossilva.self.feature.wheel.test.hasWheel
import com.jeanbarrossilva.self.feature.wheel.ui.still.CHART_TAG
import com.jeanbarrossilva.self.wheel.android.test.android
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.test.KoinTestRule

internal class WheelFragmentTests {
    private val wheelRule = WheelTestRule.android()

    private val module = module {
        singleOf(wheelRule::repository)
        singleOf(wheelRule::register)
        singleOf(wheelRule::editor)
        singleOf<WheelBoundary>(::TestWheelBoundary)
    }
    private val koinRule = KoinTestRule.create { modules(module) }
    private val composeRule = createEmptyComposeRule()
    private val activityScenarioRule = ActivityScenarioRule(TestWheelActivity::class.java)

    @get:Rule
    val ruleChain: RuleChain? = RuleChain
        .outerRule(wheelRule)
        .around(koinRule)
        .around(composeRule)
        .around(activityScenarioRule)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loadsWheelWhenRegistered() {
        runTest { wheelRule.register.register("Wheel") }
        composeRule.onNodeWithTag(CHART_TAG).assert(hasWheel(hasAreas("Wheel")))
    }
}
