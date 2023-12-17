plugins {
    val kotlinVersion = "1.8.10"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.serialization") version kotlinVersion

    id("net.mamoe.mirai-console") version "2.16.0";
}

group = "org.laolittle.plugin.joinorquit"
version = "2.0.0"

repositories {
    if (System.getenv("CI")?.toBoolean() != true) {
        maven("https://maven.aliyun.com/repository/public")
    }
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
}

mirai {
    noTestCore = true
    setupConsoleTestRuntime {
        classpath = classpath.filter {
            !it.nameWithoutExtension.startsWith("mirai-core-jvm")
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("io.ktor:ktor-client-core:1.5.4")
    implementation("io.ktor:ktor-client-json:1.5.4")
    implementation("io.ktor:ktor-client-serialization:1.5.4")
    implementation("io.ktor:ktor-client-okhttp:1.5.4")
    val overflowVersion = "2.16.0-00fd581-SNAPSHOT"
    compileOnly("top.mrxiaom:overflow-core-api:$overflowVersion")
    testConsoleRuntime("top.mrxiaom:overflow-core:$overflowVersion")
}

tasks {
    compileKotlin {
        kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
}