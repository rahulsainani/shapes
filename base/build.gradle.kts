plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(project(Modules.database))

    // Kotlin
    implementation(Libs.kotlin_stdlib_jdk7)
    implementation(Libs.coreKtx)

    // Logging
    implementation(Libs.timber)

    // Rx
    implementation(Libs.rxJava)

    // Dependency Injection
    implementation(Libs.dagger)
    kapt(Libs.daggerCompiler)
    implementation(Libs.jsr305)

    // AndroidX
    implementation(Libs.lifecycleViewmodel)
    implementation(Libs.lifecycleViewmodelKtx)
    implementation(Libs.lifecycleCommonJava8)
    implementation(Libs.lifecycleExtensions)
    implementation(Libs.material)

    // Database
    implementation(Libs.room)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
