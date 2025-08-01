import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
}


kotlin {

    androidLibrary {
        namespace = "dev.skumar.letschat.core.presentation"
        compileSdk = libs.versions.android.compileSdk.get().toInt()
        minSdk = libs.versions.android.minSdk.get().toInt()
    }

    jvm("desktop")

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }


    sourceSets {

        val commonMain by getting {
            dependencies {
                implementation(projects.core.domain)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
//                implementation(compose.components.resources)
//                implementation(compose.components.uiToolingPreview)
                implementation(libs.kotlinx.serialization.json)
            }
        }

    }
}
