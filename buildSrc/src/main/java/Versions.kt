import org.gradle.api.JavaVersion

object Versions {
    const val AURELIUS = "1.7.0"
    const val COMPOSE_COMPILER = "1.4.3"
    const val GRADLE = "7.4.2"
    const val KOTLIN = "1.8.10"

    val java = JavaVersion.VERSION_11

    object Self {
        const val CODE = 1
        const val NAME = "1.0.0"
        const val SDK_COMPILE = 33
        const val SDK_MIN = 21
        const val SDK_TARGET = SDK_COMPILE
    }
}
