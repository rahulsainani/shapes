plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(project(Modules.base))
    implementation(project(Modules.database))

    // Dependency Injection
    kapt(Libs.daggerCompiler)

    // AndroidX
    implementation(Libs.appCompat)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerview)
    implementation(Libs.lottie)

    // Testing
    testImplementation(project(Modules.testCore))
}
