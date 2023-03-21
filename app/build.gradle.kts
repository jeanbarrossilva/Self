plugins {
    id("com.android.application")
    id("kotlin-android")
}

@Suppress("UnstableApiUsage")
android {
    namespace = Metadata.namespace("app")
    compileSdk = Versions.Self.SDK_COMPILE

    defaultConfig {
        applicationId = Metadata.NAMESPACE
        minSdk = Versions.Self.SDK_MIN
        targetSdk = Versions.Self.SDK_TARGET
        versionCode = Versions.Self.CODE
        versionName = Versions.Self.NAME
        testInstrumentationRunner = Libraries.TEST_RUNNER
    }

    buildTypes {
        getByName(Variants.RELEASE) {
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
    implementation(project(":wheel-in-memory"))
    implementation(Libraries.AURELIUS)
    implementation(Libraries.KOIN)
    implementation(Libraries.NAVIGATION_FRAGMENT)
    implementation(Libraries.NAVIGATION_UI)
    implementation(Libraries.PREFERENCE)
}
