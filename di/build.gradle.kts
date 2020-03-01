plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(project(Modules.database))
    implementation(project(Modules.base))

    // Kotlin
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.coreKtx)

    // Dependency Injection
    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
    implementation(Libs.jsr305)

    // AndroidX
    implementation(Libs.lifecycleViewmodel)

    // Database
    implementation(Libs.room)
}
