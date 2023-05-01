plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("feature.wheel.test")
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
    implementation(project(":feature-wheel"))
    implementation(Libraries.COMPOSE_UI_TEST_JUNIT4)
}
