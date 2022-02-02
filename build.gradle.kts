buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.google.dagger:hilt-android-gradle-plugin:${me.vislavy.vkgram.build_src.Libs.Google.Hilt.Version}")
        classpath("org.jetbrains.kotlin:kotlin-serialization:1.6.0")
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}