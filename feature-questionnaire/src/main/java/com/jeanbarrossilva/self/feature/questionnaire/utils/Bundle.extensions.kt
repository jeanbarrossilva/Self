package com.jeanbarrossilva.self.feature.questionnaire.utils

import android.os.Bundle
import android.os.Parcelable

@Suppress("DEPRECATION")
internal inline fun <reified T : Parcelable> Bundle.getParcelableOrThrow(key: String): T {
    return when (val value = this[key]) {
        null -> unprovided(key)
        !is Parcelable -> nonParcelable(key)
        !is T -> non<T>(key)
        else -> value
    }
}

private fun unprovided(key: String): Nothing {
    throw IllegalArgumentException("\"$key\" has not been provided.")
}

private fun nonParcelable(key: String): Nothing {
    throw IllegalArgumentException("\"$key\" is provided but is not a Parcelable.")
}

private inline fun <reified T : Parcelable> non(key: String): Nothing {
    throw IllegalArgumentException(
        "\"$key\" is provided as a Parcelable but is not a ${T::class.simpleName}."
    )
}
