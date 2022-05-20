plugins {
    id("com.android.application")
    kotlin("kapt")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("plugin.serialization") version "1.6.21"
}

android {
    compileSdk = 31
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = "com.formation.app"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        signingConfig = signingConfigs.getByName("debug")
    }

    flavorDimensions.add("environment")

    productFlavors {
        create("dev") {
            dimension = "environment"
            resValue("string", "back_url", "https://parseapi.back4app.com/")
        }
        create("prod") {
            dimension = "environment"
            resValue("string", "back_url", "https://parseapi.back4app.com/")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        useIR = true
    }
    buildFeatures {
        compose = true
    }
    buildFeatures.dataBinding = true
    buildFeatures.viewBinding = true
    composeOptions {
        kotlinCompilerExtensionVersion = "1.0.2"
    }
}

dependencies {

    // CORE
    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")

    // ALERT
    implementation("com.tapadoo.android:alerter:6.1.0")

    // CAMERA
    implementation("androidx.camera:camera-core:1.0.2")
    implementation("androidx.camera:camera-camera2:1.0.2")
    implementation("androidx.camera:camera-lifecycle:1.0.2")
    implementation("androidx.camera:camera-view:1.1.0-beta03")
    implementation("androidx.camera:camera-extensions:1.1.0-beta03")

    //ZOOM
    implementation("com.github.stfalcon-studio:StfalconImageViewer:v1.0.1")

    // COROUTINES
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    // HILT
    implementation("com.google.dagger:hilt-android:2.40.5")
    implementation("androidx.hilt:hilt-common:1.0.0")
    implementation("androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    kapt("com.google.dagger:hilt-compiler:2.40.5")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

    // COMPOSE
    implementation("androidx.compose.ui:ui:1.2.0-alpha08")
    implementation("androidx.compose.material:material:1.2.0-alpha08")
    implementation("androidx.compose.material:material-icons-extended:1.2.0-alpha08")
    implementation("androidx.compose.foundation:foundation:1.2.0-alpha08")
    implementation("androidx.compose.foundation:foundation-layout:1.2.0-alpha08")
    implementation("androidx.compose.animation:animation:1.2.0-alpha08")
    implementation("androidx.compose.runtime:runtime:1.2.0-alpha08")
    implementation("androidx.compose.runtime:runtime-livedata:1.2.0-alpha08")
    implementation("androidx.navigation:navigation-compose:2.5.0-beta01")
    implementation("androidx.activity:activity-compose:1.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0-beta01")
    implementation("com.google.accompanist:accompanist-permissions:0.16.0")
    implementation("io.coil-kt:coil-compose:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:1.0.2") {
        version {
            // TODO: Remove this when Android Studio has become compatible again
            strictly("1.0.0-beta09")
        }
    }

    // ARCHITECTURE COMPONENTS
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.4.1")

    // ACCOMPANIST
    implementation("com.google.accompanist:accompanist-coil:0.13.0")
    implementation("com.google.accompanist:accompanist-pager:0.23.1")

    // PREFERENCES
    implementation("androidx.preference:preference-ktx:1.2.0")

    implementation("com.google.android.material:material:1.6.0")
    implementation("androidx.test:runner:1.4.0")

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("com.squareup.okhttp3:okhttp:4.9.3")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

}
