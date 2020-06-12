plugins {
    id("com.android.library")
    kotlin("android")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
    kotlin("android.extensions")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(project(Modules.base))
    implementation(project(Modules.database))

    // Logging
    implementation(Libs.timber)

    // Dependency Injection
    implementation(Libs.hiltViewModel)
    kapt(Libs.androidXHiltCompiler)
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    // AndroidX
    implementation(Libs.appCompat)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.lifecycleViewmodelKtx)
    implementation(Libs.androidXActivity)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.recyclerview)

    // Animations
    implementation(Libs.lottie)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
