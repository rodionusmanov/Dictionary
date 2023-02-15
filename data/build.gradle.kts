plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.datamodel"
    compileSdk = 32

    defaultConfig {
        minSdk = 26
        targetSdk = 32

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Deps.RETROFIT_CONVERTER_GSON_DEP)

    implementation(Deps.CORE_KTX_DEP)
    implementation(Deps.APPCOMPAT_DEP)
    implementation(Deps.MATERIAL_DEP)
    implementation(Deps.CONSTRAINT_LAYOUT_DEP)
    testImplementation(Deps.JUNIT_JUNIT_DEP)
    androidTestImplementation(Deps.EXT_JUNIT_DEP)
    androidTestImplementation(Deps.ESPRESSO_DEP)
}