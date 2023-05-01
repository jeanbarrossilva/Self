package com.jeanbarrossilva.self.feature.wheel.test.assertion

import com.jeanbarrossilva.self.feature.wheel.domain.wheel.WheelMetadata
import com.jeanbarrossilva.self.feature.wheel.test.utils.flatten

/** Asserts isolated aspects of the wheel node. **/
abstract class WheelNodeAssertion private constructor() {
    /**
     * Asserts that the wheel displays the given [areaNames].
     *
     * @param areaNames Names of the areas that the wheel is supposed to display.
     **/
    internal class AreaNames(private val areaNames: HashSet<String>) : WheelNodeAssertion() {
        private var missing = emptyList<String>()

        override fun isValid(metadata: WheelMetadata): Boolean {
            missing = areaNames.filterNot { it in metadata.x }
            return missing.isEmpty()
        }

        override fun getMessage(metadata: WheelMetadata): String {
            return "Wheel didn't display the following area names: $missing."
        }
    }

    /**
     * Asserts that the wheel displays the given [areaAttentions].
     *
     * @param areaAttentions Attentions of the areas that the wheel is supposed to display.
     **/
    internal class AreaAttentions(private val areaAttentions: HashSet<Float>) :
        WheelNodeAssertion() {
        private var missing = emptyList<Float>()

        override fun isValid(metadata: WheelMetadata): Boolean {
            missing = areaAttentions.filterNot { it in metadata.y.flatten() }
            return missing.isEmpty()
        }

        override fun getMessage(metadata: WheelMetadata): String {
            return "Wheel didn't display the following area attentions: $missing."
        }
    }

    /**
     * Combines both [WheelNodeAssertion]s.
     *
     * @param other [WheelNodeAssertion] to be combined with this one.
     **/
    infix fun and(other: WheelNodeAssertion): WheelNodeAssertion {
        return object : WheelNodeAssertion() {
            private var thisIsValid: Boolean? = null
            private var otherIsValid: Boolean? = null

            override fun isValid(metadata: WheelMetadata): Boolean {
                @Suppress("LocalVariableName")
                val _thisIsValid = this@WheelNodeAssertion.isValid(metadata)

                @Suppress("LocalVariableName")
                val _otherIsValid = other.isValid(metadata)

                thisIsValid = _thisIsValid
                otherIsValid = _otherIsValid
                return _thisIsValid && _otherIsValid
            }

            override fun getMessage(metadata: WheelMetadata): String {
                return if (!requireNotNull(thisIsValid)) {
                    this@WheelNodeAssertion.getMessage(metadata)
                } else if (!requireNotNull(otherIsValid)) {
                    other.getMessage(metadata)
                } else {
                    "Unknown error."
                }
            }
        }
    }

    /**
     * Asserts this [WheelNodeAssertion]'s criteria against the [metadata].
     *
     * @param metadata [WheelMetadata] to be asserted.
     * @throws AssertionError If the node doesn't meet the criteria.
     **/
    internal infix fun on(metadata: WheelMetadata) {
        assert(isValid(metadata)) {
            getMessage(metadata)
        }
    }

    /**
     * Checks if the node meets the criteria of this [WheelNodeAssertion].
     *
     * @param metadata [WheelMetadata] to be checked.
     * @throws AssertionError If the node doesn't meet the criteria.
     **/
    protected abstract fun isValid(metadata: WheelMetadata): Boolean

    /**
     * Error message to be shown in case the [metadata] doesn't match the intended state of the
     * wheel.
     *
     * @param metadata [WheelMetadata] that has caused the error.
     **/
    protected abstract fun getMessage(metadata: WheelMetadata): String
}
