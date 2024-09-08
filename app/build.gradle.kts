plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.ksp)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.androidx.navigation.safeargs)
}

android {
    namespace = "by.baranovdev.testgithub"
    compileSdk = 34

    defaultConfig {
        applicationId = "by.baranovdev.testgithub"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"https://api.github.com/\"")

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
        }
    }

    buildFeatures {
        buildConfig = true
        viewBinding = true
//        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)

    //Dagger
    implementation(libs.dagger.android)
    implementation(libs.dagger.android.support)
    ksp(libs.dagger.android.processor)
    ksp(libs.dagger.android.compiler)

    //Gson
    implementation(libs.gson.converter)
    implementation(libs.gson)

    //Network
    implementation(libs.retrofit)
    implementation(libs.okhttp)

    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.androidx)
    ksp(libs.room.compiler)

    //Groupie
    implementation(libs.recyclerview)
    implementation(libs.groupie)
    implementation(libs.groupie.viewbinding)
    implementation(libs.groupie.kext)

    //Picasso
    implementation(libs.picasso)

    //Lottie
    implementation(libs.lottie)
}