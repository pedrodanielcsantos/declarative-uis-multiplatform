plugins {
    kotlin("multiplatform") version "1.5.30"
    id("maven-publish")
}

group = "com.pedrosantos"
version = "1.0.0"

repositories {
    mavenCentral()
    mavenLocal()
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }

    val appleFrameworkName = "declarative_multiplatformist_common_ios"
    configure(listOf(iosX64(), iosArm64())) {
        binaries.framework { baseName = appleFrameworkName }
    }

    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.2.1")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
            }
        }
    }

    tasks.register<Delete>("cleanXCFramework") {
        delete = setOf("xcframework/$appleFrameworkName.xcframework")
    }

    tasks.register<Exec>("buildXCFramework") {
        dependsOn("assemble")
        dependsOn("cleanXCFramework")
        commandLine(
            "xcodebuild",
            "-create-xcframework",
            "-output",
            "xcframework/$appleFrameworkName.xcframework",
            "-framework",
            buildDir.resolve("bin/iosX64/debugFramework/$appleFrameworkName.framework"),
            "-debug-symbols",
            buildDir.resolve("bin/iosX64/debugFramework/$appleFrameworkName.framework.dSYM"),
            "-framework",
            buildDir.resolve("bin/iosArm64/debugFramework/$appleFrameworkName.framework"),
            "-debug-symbols",
            buildDir.resolve("bin/iosArm64/debugFramework/$appleFrameworkName.framework.dSYM")
        )
    }
}
