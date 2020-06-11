plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)

    implementation(project(Modules.database))
    implementation(project(Modules.shapes))
    implementation(project(Modules.base))

    implementation(Libs.room)
    implementation(Libs.appCompat)
    implementation(Libs.hilt)
    kapt(Libs.hiltCompiler)

    implementation(Libs.timber)
}
