//import sun.management.ConnectorAddressLink.export

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()
    iosX64()
    iosArm64()
//    val iosX64 = iosX64()
//    val iosArm64 = iosArm64()
    //iosSimulatorArm64() sure all ios dependencies support this target
//    targets {
//        configure(listOf(iosX64, iosArm64)) {
//            binaries {
//                staticLib {
//                    filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
//                        .forEach {
//                            it.freeCompilerArgs += "-Xexport-kdoc"
//                            // Inject Export Begin
//                            it.export("com.squareup.sqldelight:native-driver:1.5.3")
//                            // Inject Export End
//                        }
//                }
//            }
//        }
//    }
//    targets {
//        configure(listOf(iosX64, iosArm64)) {
//            binaries.withType(org.jetbrains.kotlin.gradle.plugin.mpp.Framework::class.java) {
////                export(project(":athlive-base"))
//                export("com.squareup.sqldelight:native-driver:1.5.3")
//            }
//        }
//    }

//    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>().forEach{
//        it.binaries.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.Framework>()
//            .forEach { lib ->
////                lib.isStatic = false
//                lib.linkerOpts.add("-lsqlite3")
//            }
//    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:runtime:1.5.3")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("com.squareup.sqldelight:android-driver:1.5.3")
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        //val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            dependencies {
                implementation("com.squareup.sqldelight:native-driver:1.5.3")
            }
            //iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        //val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            //iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

//apply {
//    plugin("com.squareup.sqldelight")
//}
//apply(plugin('com.squareup.sqldelight'))
//apply plugin: 'com.squareup.sqldelight'

sqldelight {
    database("AppDatabase") {
        packageName = "com.example.myapplication"
    }
    linkSqlite = true
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 21
        targetSdk = 31
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
}