package com.jeanbarrossilva.self.feature.wheel.test.assertion

/**
 * Creates a [WheelNodeAssertion] that asserts that the wheel displays the given [areaNames].
 *
 * @param areaNames Names of the areas that the wheel is supposed to display.
 **/
fun hasAreas(vararg areaNames: String): WheelNodeAssertion {
    val areaNamesAsHashSet = areaNames.toHashSet()
    return WheelNodeAssertion.AreaNames(areaNamesAsHashSet)
}

/**
 * Creates a [WheelNodeAssertion] that asserts that the wheel displays the given [areaAttentions].
 *
 * @param areaAttentions Attentions of the areas that the wheel is supposed to display.
 **/
fun hasAttentions(vararg areaAttentions: Float): WheelNodeAssertion {
    val areaAttentionsAsHashSet = areaAttentions.toHashSet()
    return WheelNodeAssertion.AreaAttentions(areaAttentionsAsHashSet)
}
