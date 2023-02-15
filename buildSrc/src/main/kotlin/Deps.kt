import org.gradle.api.JavaVersion

object Deps {

    object Config {
        const val application_id = "com.example.dictionary"
        const val min_sdk = 26
        const val target_sdk = 32
        const val compile_sdk = 32
        val java_version = JavaVersion.VERSION_1_8
    }
    object Releases {
        const val version_code = 1
        const val version_name = "1.0"
    }
    object Modules {
        const val app = ":app"
        const val core = ":core"
        const val model = ":model"
        const val repository = ":repository"
        const val utils = ":utils"

        const val historyScreen = ":historyScreen"
    }

    //Rx-Java
    private const val RXANDROID_VERSION = "3.0.0"
    private const val RXJAVA_VERSION = "3.0.0"

    //Retrofit 2
    private const val RETROFIT_VERSION = "2.9.0"
    private const val RETROFIT_CONVERTER_GSON_VERSION = "2.9.0"
    private const val RETROFIT_ADAPTER_RXJAVA3_VERSION = "2.9.0"
    private const val GLIDE_VERSION = "4.11.0"
    private const val OKHTTP3_LOGGING_INTERCEPTOR_VERSION = "3.12.1"

    //Dagger 2
    private const val DAGGER_VERSION = "2.42"
    private const val DAGGER_COMPILER_VERSION = "2.42"
    private const val DAGGER_ANDROID_SUPPORT_VERSION = "2.42"
    private const val DAGGER_ANDROID_PROCESSOR_VERSION = "2.42"

    //Koin
    private const val KOIN_CORE_VERSION = "3.1.2"
    private const val KOIN_ANDROID_VERSION = "3.1.2"
    private const val KOIN_ANDROID_COMPAT_VERSION = "3.1.2"

    //Coroutines
    private const val COROUTINES_CORE_VERSION = "1.5.1"
    private const val COROUTINES_ANDROID_VERSION = "1.6.1"
    private const val RETROFIT_COROUTINES_ADAPTER_VERSION = "0.9.2"

    //Room
    private const val ROOM_RUNTIME_VERSION = "2.3.0"
    private const val ROOM_COMPILER_VERSION = "2.3.0"
    private const val ROOM_KTX_VERSION = "2.3.0"

    //Coil
    private const val COIL_VERSION = "1.0.0"

    //Core
    private const val CORE_KTX_VERSION = "1.7.0"
    private const val APPCOMPAT_VERSION = "1.5.1"
    private const val MATERIAL_VERSION = "1.7.0"
    private const val CONSTRAINT_LAYOUT_VERSION = "2.1.4"
    private const val JUNIT_JUNIT_VERSION = "4.13.2"
    private const val EXT_JUNIT_VERSION = "1.1.4"
    private const val ESPRESSO_VERSION = "3.5.0"
    private const val LIFECYCLE_VIEWMODEL_VERSION = "2.5.1"

    //Rx-Java
    const val RXANDROID_DEP = "io.reactivex.rxjava3:rxandroid:$RXANDROID_VERSION"
    const val RXJAVA_DEP = "io.reactivex.rxjava3:rxjava:$RXJAVA_VERSION"

    //Retrofit 2
    const val RETROFIT_DEP = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
    const val RETROFIT_CONVERTER_GSON_DEP = "com.squareup.retrofit2:converter-gson:$RETROFIT_CONVERTER_GSON_VERSION"
    const val RETROFIT_ADAPTER_RXJAVA3_DEP = "com.squareup.retrofit2:adapter-rxjava3:$RETROFIT_ADAPTER_RXJAVA3_VERSION"
    const val GLIDE_DEP = "com.github.bumptech.glide:glide:$GLIDE_VERSION"
    const val OKHTTP3_LOGGING_INTERCEPTOR_DEP = "com.squareup.okhttp3:logging-interceptor:$OKHTTP3_LOGGING_INTERCEPTOR_VERSION"

    //Dagger 2
    const val DAGGER_DEP = "com.google.dagger:dagger:$DAGGER_VERSION"
    const val DAGGER_COMPILER_DEP = "com.google.dagger:dagger-compiler:$DAGGER_COMPILER_VERSION"
    const val DAGGER_ANDROID_SUPPORT_DEP = "com.google.dagger:dagger-android-support:$DAGGER_ANDROID_SUPPORT_VERSION"
    const val DAGGER_ANDROID_PROCESSOR_DEP = "com.google.dagger:dagger-android-processor:$DAGGER_ANDROID_PROCESSOR_VERSION"

    //Koin
    const val KOIN_CORE_DEP = "io.insert-koin:koin-core:$KOIN_CORE_VERSION"
    const val KOIN_ANDROID_DEP = "io.insert-koin:koin-android:$KOIN_ANDROID_VERSION"
    const val KOIN_ANDROID_COMPAT_DEP = "io.insert-koin:koin-android-compat:$KOIN_ANDROID_COMPAT_VERSION"

    //Coroutines
    const val COROUTINES_CORE_DEP = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_CORE_VERSION"
    const val COROUTINES_ANDROID_DEP = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINES_ANDROID_VERSION"
    const val RETROFIT_COROUTINES_ADAPTER_DEP = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:$RETROFIT_COROUTINES_ADAPTER_VERSION"

    //Room
    const val ROOM_RUNTIME_DEP = "androidx.room:room-runtime:$ROOM_RUNTIME_VERSION"
    const val ROOM_COMPILER_DEP = "androidx.room:room-compiler:$ROOM_COMPILER_VERSION"
    const val ROOM_KTX_DEP = "androidx.room:room-ktx:$ROOM_KTX_VERSION"

    //Coil
    const val COIL_DEP = "io.coil-kt:coil:$COIL_VERSION"

    //Core
    const val CORE_KTX_DEP = "androidx.core:core-ktx:$CORE_KTX_VERSION"
    const val APPCOMPAT_DEP = "androidx.appcompat:appcompat:$APPCOMPAT_VERSION"
    const val MATERIAL_DEP = "com.google.android.material:material:$MATERIAL_VERSION"
    const val CONSTRAINT_LAYOUT_DEP = "androidx.constraintlayout:constraintlayout:$CONSTRAINT_LAYOUT_VERSION"
    const val JUNIT_JUNIT_DEP = "junit:junit:$JUNIT_JUNIT_VERSION"
    const val EXT_JUNIT_DEP = "androidx.test.ext:junit:$EXT_JUNIT_VERSION"
    const val ESPRESSO_DEP = "androidx.test.espresso:espresso-core:$ESPRESSO_VERSION"
    const val LIFECYCLE_VIEWMODEL_DEP = "androidx.lifecycle:lifecycle-viewmodel-ktx:$LIFECYCLE_VIEWMODEL_VERSION"
}