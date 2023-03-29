plugins {
    id("com.android.library")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("wheel.android")
    compileSdk = Versions.Self.SDK_COMPILE

    defaultConfig {
        minSdk = Versions.Self.SDK_MIN

        @Suppress("Deprecation")
        targetSdk = Versions.Self.SDK_TARGET

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }
}

dependencies {
    kapt("androidx.room:room-compiler:${Versions.ROOM}")

    api(project(":wheel-core"))
    api(Libraries.ROOM)

    implementation(Libraries.KOTLINX_COROUTINES_CORE)
    implementation(Libraries.KOIN)
}
