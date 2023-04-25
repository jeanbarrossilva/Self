plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
}

dependencies {
    api(Libraries.JUNIT)

    implementation(project(":wheel-core"))
    implementation(Libraries.KOTLINX_COROUTINES_TEST)
}
