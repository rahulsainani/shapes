plugins {
    id("com.android.application")
    kotlin("android")
}
apply(from = "${rootProject.projectDir}/android.gradle")

dependencies {
    implementation(Libs.kotlin_stdlib_jdk7)

    implementation(project(Modules.shapes))
    implementation(project(Modules.base))

    implementation(Libs.timber)
}
