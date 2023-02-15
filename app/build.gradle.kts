plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    defaultConfig {
        applicationId = "com.example.dictionary"
        minSdk = 26
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        viewBinding = true
    }
}

dependencies {

    //Rx-Java
    implementation(Deps.RXANDROID_DEP)
    implementation(Deps.RXJAVA_DEP)

    // Retrofit 2
    implementation(Deps.RETROFIT_DEP)
    implementation(Deps.RETROFIT_CONVERTER_GSON_DEP)
    implementation(Deps.RETROFIT_ADAPTER_RXJAVA3_DEP)
    implementation(Deps.GLIDE_DEP)
    implementation(Deps.OKHTTP3_LOGGING_INTERCEPTOR_DEP)

    //Dagger 2
    implementation(Deps.DAGGER_DEP)
    kapt(Deps.DAGGER_COMPILER_DEP)
    implementation(Deps.DAGGER_ANDROID_SUPPORT_DEP)
    kapt(Deps.DAGGER_ANDROID_PROCESSOR_DEP)

    //Koin
    implementation(Deps.KOIN_CORE_DEP)
    implementation(Deps.KOIN_ANDROID_DEP)
    implementation(Deps.KOIN_ANDROID_COMPAT_DEP)

    //Coroutines
    implementation(Deps.COROUTINES_CORE_DEP)
    implementation(Deps.COROUTINES_ANDROID_DEP)
    implementation(Deps.RETROFIT_COROUTINES_ADAPTER_DEP)

    //Room
    implementation(Deps.ROOM_RUNTIME_DEP)
    kapt(Deps.ROOM_COMPILER_DEP)
    implementation(Deps.ROOM_KTX_DEP)

    //Coil
    implementation(Deps.COIL_DEP)

    implementation(Deps.CORE_KTX_DEP)
    implementation(Deps.APPCOMPAT_DEP)
    implementation(Deps.MATERIAL_DEP)
    implementation(Deps.CONSTRAINT_LAYOUT_DEP)
    testImplementation(Deps.JUNIT_JUNIT_DEP)
    androidTestImplementation(Deps.EXT_JUNIT_DEP)
    androidTestImplementation(Deps.ESPRESSO_DEP)

    implementation(Deps.LIFECYCLE_VIEWMODEL_DEP)
}