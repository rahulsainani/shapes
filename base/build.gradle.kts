plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(project(Modules.database))

    // Kotlin
    api(Libs.kotlin_stdlib_jdk7)
    api(Libs.coreKtx)

    // Logging
    api(Libs.timber)

    // Rx
    api(Libs.rxJava)

    // Dependency Injection
    api(Libs.dagger)
    kapt(Libs.daggerCompiler)
    api(Libs.jsr305)

    // AndroidX
    api(Libs.lifecycleViewmodel)
    api(Libs.lifecycleCommonJava8)
    api(Libs.lifecycleExtensions)
    api(Libs.material)

    // Database
    implementation(Libs.room)

    // Testing
    testImplementation(project(Modules.testCore))
    testRuntimeOnly(Libs.junitJupiterEngine)
}
