object Metadata {
    const val NAMESPACE = "com.jeanbarrossilva.self"

    fun namespace(suffix: String): String {
        return "$NAMESPACE.$suffix"
    }
}
