package com.jeanbarrossilva.self.app

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.jeanbarrossilva.self.app.fixtures.answerQuestionnaire
import com.jeanbarrossilva.self.app.fixtures.awaitNavigationTo
import com.jeanbarrossilva.self.app.module.BoundaryModule
import com.jeanbarrossilva.self.app.module.CoreModule
import com.jeanbarrossilva.self.feature.wheel.domain.FeatureArea
import com.jeanbarrossilva.self.feature.wheel.ui.still.CHART_TAG
import com.jeanbarrossilva.self.wheel.android.test.android
import com.jeanbarrossilva.self.wheel.core.test.WheelTestRule
import kotlin.coroutines.cancellation.CancellationException
import kotlin.time.Duration.Companion.seconds
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.RuleChain
import org.koin.test.KoinTestRule

internal class WheelToQuestionnaireTests {
    private val wheelRule = WheelTestRule.android()
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
            composeRule.awaitNavigationTo(R.id.questionnaire_fragment)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test(expected = CancellationException::class)
    fun doesNotGetIntoQuestionnaireLoop() {
        runTest {
            composeRule.awaitNavigationTo(R.id.questionnaire_fragment)
            composeRule.answerQuestionnaire()
            composeRule.awaitNavigationTo(R.id.wheel_fragment)
            composeRule.awaitNavigationTo(this, R.id.questionnaire_fragment, timeout = 2.seconds)
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun loadsWheelAfterAnsweringQuestionnaire() {
        runTest {
            composeRule.awaitNavigationTo(R.id.questionnaire_fragment)
            composeRule.answerQuestionnaire()
            composeRule.awaitNavigationTo(R.id.wheel_fragment)
            composeRule.onNodeWithTag(CHART_TAG, useUnmergedTree = true).assertTextEquals(
                *FeatureArea.samples.map(FeatureArea::name).toTypedArray(),
                includeEditableText = false
            )
        }
    }
}
