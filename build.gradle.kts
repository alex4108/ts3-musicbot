import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar

plugins {
    application
    kotlin("jvm") version "1.8.10"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    implementation("org.json:json:20220924")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-jvm:1.6.3-native-mt")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-javafx:1.6.3-native-mt")
    implementation("com.github.bettehem:ts3j:master-SNAPSHOT")
}

// JavaFX module to include
javafx {
    version = "11.0.2"
    modules = listOf("javafx.controls")
}

application {
    // Define the main class for the application.
    mainClass.set("ts3_musicbot.Main")
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "11"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "11"
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("shadow")
    archiveFileName.set("ts3-musicbot.jar")
    mergeServiceFiles()
    minimize()
    manifest {
        attributes(mapOf("Main-Class" to "ts3_musicbot.Main"))
    }
}

tasks {
    assemble {
        dependsOn(shadowJar)
    }
    startScripts {
        dependsOn(shadowJar)
    }
    distTar {
        enabled = false
    }
    distZip {
        enabled = false
    }
    jar {
        enabled = false
    }
}
