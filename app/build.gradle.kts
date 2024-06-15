plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)

    id ("kotlin-kapt")
    kotlin("kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.loginproject"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.loginproject"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.navigation.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation("androidx.compose.material:material-icons-extended:1.0.0") // font
//    implementation ("io.coil-kt:coil-compose: ")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")         //  retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")  //  Gson Convertor
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")   // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")

    implementation("androidx.core:core-splashscreen:1.0.1")  // Splash Screen
    implementation ("androidx.compose.ui:ui-text-google-fonts:1.6.7")  // google font

    // CameraX
    implementation ("androidx.camera:camera-camera2:1.0.1")
    implementation ("androidx.camera:camera-lifecycle:1.0.1")
    implementation ("androidx.camera:camera-view:1.0.0-alpha27")

    // Coil
//    implementation ("io.coil-kt:coil-compose:1.4.0")
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    //okhttp
    implementation ("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    //moshi
    implementation ("com.squareup.moshi:moshi-kotlin:1.15.1")

    //room database
    implementation ("androidx.room:room-runtime:2.4.0")
    implementation ("androidx.room:room-ktx:2.4.0")

    implementation ("com.google.dagger:hilt-android:2.44")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    kapt ("com.google.dagger:hilt-android-compiler:2.44")
    kapt ("androidx.hilt:hilt-compiler:1.2.0")
    kapt("androidx.room:room-compiler:2.6.1")

}

kapt {
    correctErrorTypes = true
}