package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.view.View
import android.view.ViewGroup

/**
 * Creates [ViewGroup.LayoutParams] of type [T] with the given [size].
 *
 * @param size Dimension to be applied to both the [View]'s [width][View.getWidth] and
 * [height][View.getHeight].
 * @throws NoSuchMethodException When [T] doesn't have a constructor with two [Int] values.
 **/
internal inline fun <reified T : ViewGroup.LayoutParams> layoutParamsOf(size: Int): T {
    return layoutParamsOf(width = size, height = size)
}

/**
 * Creates [ViewGroup.LayoutParams] of type [T] with the given [width] and [height] dimensions.
 *
 * @param width How wide the [View] should be.
 * @param height How tall the [View] should be.
 * @throws NoSuchMethodException When [T] doesn't have an ([Int], [Int]) constructor.
 **/
internal inline fun <reified T : ViewGroup.LayoutParams> layoutParamsOf(width: Int, height: Int):
    T {
    return T::class
        .constructors
        .find { it.parameters == listOf(Int::class, Int::class) }
        ?.call(width, height)
        ?: throw NoSuchMethodException(
            "${T::class.qualifiedName} doesn't have an (Int, Int) constructor."
        )
}
