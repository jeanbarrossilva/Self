import org.gradle.api.JavaVersion

object Versions {
    const val ACCOMPANIST = "0.31.0-alpha"
    const val AURELIUS = "1.7.0"
    const val COMPOSE_COLLAPSING_TOOLBAR = "2.3.5"
    const val COMPOSE_COMPILER = "1.4.4"
    const val COMPOSE_FOUNDATION = "1.4.0"
    const val GRADLE = "7.4.2"
    const val JUNIT = "4.13.2"
    const val KOIN = "3.3.3"
    const val KOTLIN = "1.8.10"
    const val KOTLINX_COROUTINES = "1.6.4"
    const val LOADABLE = "1.0.0"
    const val NAVIGATION = "2.5.3"
    const val ROOM = "2.5.1"
    const val TURBINE = "0.12.1"
    const val VICO = "1.6.4"
    const val VIEWPAGER2 = "1.0.0"

    val java = JavaVersion.VERSION_11

    object Self {
        const val CODE = 1
        const val NAME = "1.0.0"
        const val SDK_COMPILE = 33
        const val SDK_MIN = 23
        const val SDK_TARGET = SDK_COMPILE
    }
}
