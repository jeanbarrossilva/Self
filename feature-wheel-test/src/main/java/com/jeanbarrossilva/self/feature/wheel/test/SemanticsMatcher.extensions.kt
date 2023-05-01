package com.jeanbarrossilva.self.feature.wheel.test

import androidx.compose.ui.test.SemanticsNodeInteraction
import com.jeanbarrossilva.self.feature.wheel.domain.wheel.WheelMetadata
import com.jeanbarrossilva.self.feature.wheel.test.assertion.WheelNodeAssertion

/**
 * Runs the given [assertion] on the wheel node.
 *
 * @param assertion [WheelNodeAssertion] to be run.
 **/
fun SemanticsNodeInteraction.assertWheel(assertion: WheelNodeAssertion) {
    val metadata = fetchSemanticsNode().config.getOrElse(WheelMetadata.SemanticsPropertyKey) {
        throw IllegalStateException("Could not get chart metadata.")
    }
    assertion on metadata
}
