plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.dagger.hilt.plugin)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.agungkusuma.modernnewsapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.agungkusuma.modernnewsapp"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "BASE_URL", "\"${project.findProperty("BASE_URL")}\"")
        buildConfigField("String", "NEWS_API_KEY", "\"${project.findProperty("NEWS_API_KEY")}\"")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
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

    // Fragment
    implementation(libs.fragment)

    // Navigation
    implementation(libs.navigation)
    implementation(libs.navigationUi)

    // Hilt
    implementation(libs.hilt)
    kapt(libs.hiltSupport)

    // Network
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.interceptor)

    // ViewModel
    implementation(libs.viewmodel)

    // Glide
    implementation(libs.glide)
    kapt(libs.glideCompiler)

    // Skeleton Loading(Shimmer)
    implementation(libs.shimmer)

    implementation(libs.timber)
}