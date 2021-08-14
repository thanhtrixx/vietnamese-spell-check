import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "dev.trile"
version = "0.0.1"

val kotlinVersion: String by extra("1.5.20")
val ktorVersion: String by extra("1.6.1")
val logbackVersion: String by extra("1.2.3")
val javaVersion = JavaVersion.VERSION_11

java.sourceCompatibility = javaVersion
java.targetCompatibility = javaVersion

plugins {
  // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
  id("org.jetbrains.kotlin.jvm") version "1.5.20"
  id("org.jetbrains.kotlin.plugin.serialization") version "1.5.20"
  // Apply the application plugin to add support for building a CLI application in Java.
  application
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

dependencies {
  // Align versions of all Kotlin components
  implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

  // Use the Kotlin JDK 8 standard library.
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

  implementation("io.ktor:ktor-server-core:$ktorVersion")
  implementation("io.ktor:ktor-server-netty:$ktorVersion")

  implementation("ch.qos.logback:logback-classic:$logbackVersion")
  // This dependency is used by the application.
  implementation("com.google.guava:guava:30.1.1-jre")

  // Use the Kotlin test library.
  testImplementation("org.jetbrains.kotlin:kotlin-test")

  // Use the Kotlin JUnit integration.
  testImplementation("org.jetbrains.kotlin:kotlin-test-junit")


}

application {
  // Define the main class for the application.
  mainClass.set("dev.trile.vsc.AppKt")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "11"
  }
}
