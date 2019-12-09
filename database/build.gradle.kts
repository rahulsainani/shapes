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
    api(Libs.kotlin_stdlib_jdk7)
    api(Libs.coreKtx)

    // Logging
    api(Libs.timber)

    // Rx
    api(Libs.rxJava)

    // Database
    implementation(Libs.room)
    implementation(Libs.roomRx)
    kapt(Libs.roomCompiler)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
