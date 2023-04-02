plugins {
    id("com.android.library")
    id("kotlin-parcelize")
    id("org.jetbrains.kotlin.android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("feature.questionnaire")
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

    buildFeatures {
        compose = true
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.COMPOSE_COMPILER
    }
}

dependencies {
    implementation(project(":platform-ui"))
    implementation(project(":wheel-in-memory"))
    implementation(kotlin("reflect"))
    implementation(Libraries.AURELIUS)
    implementation(Libraries.COMPOSE_COLLAPSING_TOOLBAR)
    implementation(Libraries.COMPOSE_FOUNDATION)
    implementation(Libraries.KOIN)
    implementation(Libraries.NAVIGATION_FRAGMENT)
    implementation(Libraries.PREFERENCE)
    implementation(Libraries.VIEWPAGER2)

    testImplementation(Libraries.JUNIT)
}
