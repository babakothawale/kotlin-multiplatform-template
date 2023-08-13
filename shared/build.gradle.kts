plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization") version "1.8.20"
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    targetHierarchy.default()

    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
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
        }
    }

    sourceSets {
        val ktorVersion = "2.3.3"
        val commonMain by getting {
            dependencies {
                //put your multiplatform dependencies here
                implementation ("io.ktor:ktor-client-core:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.2")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }

        val androidMain by getting {
            dependencies {
                implementation ("com.google.android.material:material:1.9.0")
                implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
                implementation ("io.ktor:ktor-client-android:$ktorVersion")
            }
        }

        val iosMain by getting {
            dependencies {
                implementation ("io.ktor:ktor-client-ios:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.bk.km"
    compileSdk = 33
    defaultConfig {
        minSdk = 24
    }
}