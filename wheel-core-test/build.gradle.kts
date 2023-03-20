plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = Versions.java
    targetCompatibility = Versions.java
}

dependencies {
    implementation(project(":wheel-core"))
    implementation(Libraries.JUNIT)
    implementation(Libraries.KOTLINX_COROUTINES_TEST)
}
