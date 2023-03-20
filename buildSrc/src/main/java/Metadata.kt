object Metadata {
    const val NAMESPACE = "self"
    const val GROUP = "com.jeanbarrossilva.self"

    fun namespace(suffix: String): String {
        return "$NAMESPACE.$suffix"
    }
}
