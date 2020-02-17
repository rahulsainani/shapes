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

    // Logging
    implementation(Libs.timber)

    // Rx
    implementation(Libs.rxJava)

    // Dependency Injection
    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)

    // AndroidX
    implementation(Libs.appCompat)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleViewmodelKtx)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerview)

    // Animations
    implementation(Libs.lottie)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
