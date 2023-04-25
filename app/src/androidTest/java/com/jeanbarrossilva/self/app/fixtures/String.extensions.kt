package com.jeanbarrossilva.self.app.fixtures

/**
 * Returns this [String] with all occurrences of [other] removed.
 *
 * @param other [Char] to be removed.
 **/
internal operator fun String.minus(other: Char): String {
    return minus("$other")
}

/**
 * Returns this [String] with all occurrences of [other] removed.
 *
 * @param other [String] to be removed.
 **/
internal operator fun String.minus(other: String): String {
    return replace(other, "")
}
