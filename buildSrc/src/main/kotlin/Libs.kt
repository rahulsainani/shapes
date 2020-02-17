@Suppress("unused")
object Libs {

    const val kotlin_stdlib_jdk7 =
        "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.org_jetbrains_kotlin}"
    const val kotlin_gradle_plugin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.org_jetbrains_kotlin}"

    const val coroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.ktx}"
    const val lifecycleCommonJava8 =
        "androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifecycle}"
    const val lifecycleExtensions =
        "androidx.lifecycle:lifecycle-extensions:${Versions.androidxLifecycle}"
    const val lifecycleViewmodel =
        "androidx.lifecycle:lifecycle-viewmodel:${Versions.androidxLifecycle}"
    const val lifecycleViewmodelKtx =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    const val material = "com.google.android.material:material:${Versions.material}"

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val jsr305 = "com.google.code.findbugs:jsr305:${Versions.jsr305}"
    const val rxJava = "io.reactivex.rxjava2:rxjava:${Versions.rxJava}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val room = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRx = "androidx.room:room-rxjava2:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"

    // Testing
    const val junitJupiter = "org.junit.jupiter:junit-jupiter-api:${Versions.junitJupiter}"
    const val junitJupiterEngine = "org.junit.jupiter:junit-jupiter-engine:${Versions.junitJupiter}"
    const val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    const val mockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val assertJCore = "org.assertj:assertj-core:${Versions.assertJCore}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    // Plugins
    const val androidGradlePlugin =
        "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val buildSrcVersionsPlugin =
        "de.fayard:buildSrcVersions:${Versions.buildSrcVersionsPlugin}"
    const val ktlintGradlePlugin =
        "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlintGradlePlugin}"
}
