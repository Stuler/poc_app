plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    kotlin("plugin.serialization") version "1.9.0"
    id("dev.icerock.mobile.multiplatform-resources")
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
            export("dev.icerock.moko.resources:0.23.0")
            export("dev.icerock.moko:graphics:0.9.0")
        }
    }


    sourceSets {
        val mokoResourcesVersion = extra["moko.resources.version"] as String
        val mokoMvvmVersion = extra["moko.mvvm.version"] as String
        val mokoPermissionsVersion = extra["moko.permissions.version"] as String
        val mokoMediaVersion = extra["moko.media.version"] as String
        val mokoBiometryVersion = extra["moko.biometry.version"] as String
        val mokoGeoVersion = extra["moko.geo.version"] as String

        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)
                implementation("io.ktor:ktor-client-core:2.3.4")
                implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
                api("dev.icerock.moko:mvvm-core:0.16.1") // only ViewModel, EventsDispatcher, Dispatchers.UI
                api("dev.icerock.moko:mvvm-compose:0.16.1") // api mvvm-core, getViewModel for Compose Multiplatform
                api("dev.icerock.moko:mvvm-flow:0.16.1") // api mvvm-core, FlowViewModel, FlowEventsDispatcher
                api("dev.icerock.moko:resources:0.23.0")
                api("dev.icerock.moko:resources-compose:$mokoResourcesVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
                // fix of Could not find "shared/build/kotlinTransformedMetadataLibraries/commonMain/org.jetbrains.kotlinx-atomicfu-0.17.3-nativeInterop-8G5yng.klib"
                implementation("org.jetbrains.kotlinx:atomicfu:0.21.0")
            }
        }
        val commonTest by getting {
            dependsOn(commonMain)

        }
        val androidMain by getting{
            dependsOn(commonMain)
            dependencies{
                implementation("io.ktor:ktor-client-android:2.3.4")
            }
        }

        val iosMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation("io.ktor:ktor-client-ios:2.3.4")
            }
        }
    }
}

android {
    namespace = "com.example.mypocapp"
    compileSdk = 33
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
    defaultConfig {
        minSdk = 24
    }
}

multiplatformResources {
    multiplatformResourcesPackage = "com.pocapp_sharingRes"
}