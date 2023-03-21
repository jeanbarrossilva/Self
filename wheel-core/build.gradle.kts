plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
}

dependencies {
    implementation(Libraries.KOTLINX_COROUTINES_CORE)

    testImplementation(project(":wheel-in-memory"))
    testImplementation(project(":wheel-in-memory-test"))
    testImplementation(kotlin("test"))
    testImplementation(Libraries.KOTLINX_COROUTINES_TEST)
    testImplementation(Libraries.TURBINE)
}
