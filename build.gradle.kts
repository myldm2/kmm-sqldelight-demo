buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.0")
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("com.squareup.sqldelight:gradle-plugin:1.5.3")
    }
}

//apply {
//    plugin("com.squareup.sqldelight")
//}
////apply(plugin('com.squareup.sqldelight'))
////apply plugin: 'com.squareup.sqldelight'
//
//sqldelight {
//    database("AppDatabase") {
//        packageName = "com.example.myapplication"
//    }
//    linkSqlite = true
//}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}