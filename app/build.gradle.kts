plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
}

android {
    compileSdk = 32

    buildFeatures{
        viewBinding = true
    }

    defaultConfig {
        applicationId = "com.trdz.trdz_listRequest"
        minSdk = 21
        targetSdk = 32
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

}

dependencies {

    //Data Room
    implementation(Deps.ROOM_CORE)
    kapt(Deps.ROOM_COMPILER)
    //Server
    implementation(Deps.RETROFIT_CORE)  //Functional Requests
    implementation(Deps.RETROFIT_CONV)  //Functional Requests Converter
    implementation(Deps.RETROFIT_JSON)  //Server Json feature
    //Image
    implementation(Deps.IMAGE_GLIDE)    // Image - Glide
    implementation(Deps.IMAGE_COIL)     // Image - Coil
    implementation(Deps.IMAGE_SVG)      // Image - Coil svg
    //Koin
    implementation(Deps.KOIN_CORE)
    implementation(Deps.KOIN_ANDROID)   //Koin Features (Scope,ViewModel...)
    implementation(Deps.KOIN_COMPAT)    //Koin Java Compatibility
    //Coroutines
    implementation(Deps.COROUTINES_CORE)
    implementation(Deps.COROUTINES_ANDROID)
    //Extension
    implementation(Deps.ANDX_VM)        //MVVM ViewModel addon
    implementation(Deps.ANDX_LD)        //LiveData addon
    implementation(Deps.ANDX_FRAGMENT)  //Fragment addon
    //Code
    implementation(Deps.CODE_REFLECTION)// Reflection
    implementation(Deps.CODE_STDLIB)
    //Base
    implementation(Deps.ANDX_CORE)
    implementation(Deps.ANDX_LEGACY)    // Legacy
    implementation(Deps.ANDX_APPCOMPAT)
    implementation(Deps.ANDX_CONSTRAINT)
    implementation(Deps.AND_MATERIAL)


}