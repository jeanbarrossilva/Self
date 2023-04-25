package com.jeanbarrossilva.self.app

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.jeanbarrossilva.self.app.fixtures.minus
import com.jeanbarrossilva.self.app.fixtures.waitForDestination
import com.jeanbarrossilva.self.app.module.BoundaryModule
import com.jeanbarrossilva.self.app.module.CoreModule
import com.jeanbarrossilva.self.feature.questionnaire.step.STEP_ANSWER_TAG
import com.jeanbarrossilva.self.feature.questionnaire.step.STEP_NEXT_BUTTON_TAG
import com.jeanbarrossilva.self.feature.wheel.WheelModel
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.wheel.android.test.AndroidWheelTestRule
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTestRule

internal class WheelToQuestionnaireTests {
    private val wheelRule = AndroidWheelTestRule()
    private val koinRule = KoinTestRule.create {
        modules(
            CoreModule({ wheelRule.repository }, { wheelRule.register }, { wheelRule.editor }),
            BoundaryModule()
        )
    }
    private val composeRule = createAndroidComposeRule<SelfActivity>()

    @get:Rule
    val ruleChain: RuleChain? = RuleChain.outerRule(wheelRule).around(koinRule).around(composeRule)

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun navigatesToQuestionnaireOnNonexistentWheel() {
        runTest {
            composeRule.waitForDestination(R.id.questionnaire_fragment)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = CancellationException::class)
    fun doesNotGetIntoQuestionnaireLoop() {
        composeRule.onNodeWithTag(STEP_NEXT_BUTTON_TAG).performClick()
        FeatureArea.samples.map { WheelModel.format(it.attention) - '%' }.forEach {
            composeRule.onNodeWithTag(STEP_ANSWER_TAG).performClick()
            composeRule.onNodeWithTag(STEP_ANSWER_TAG).performTextInput(it)
            composeRule.onNodeWithTag(STEP_NEXT_BUTTON_TAG).performClick()
        }
        runTest {
            composeRule.waitForDestination(R.id.wheel_fragment)
            composeRule.waitForDestination(this, R.id.questionnaire_fragment, timeout = 2.seconds)
        }
    }
}
