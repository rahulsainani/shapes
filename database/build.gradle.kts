plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
apply(from = "${rootProject.projectDir}/android.gradle")

android {
    defaultConfig {
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf(
                    "room.schemaLocation" to "$projectDir/schemas",
                    "room.incremental" to "true"
                )
            }
        }
    }
}

dependencies {
    // Kotlin
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.coreKtx)

    // Logging
    implementation(Libs.timber)

    // Database
    implementation(Libs.room)
    implementation(Libs.roomKtx)
    kapt(Libs.roomCompiler)

    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
