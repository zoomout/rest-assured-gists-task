import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
}

plugins {
    kotlin("jvm") version "1.3.41"
    id("com.adarshr.test-logger") version "1.7.0"
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("io.rest-assured:rest-assured:4.0.0")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.9.9.3")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.9")
    testImplementation("org.testng:testng:6.14.3")
}

tasks.withType<Test> {
    useTestNG()
    testlogger {
        showStandardStreams = true
        showFullStackTraces = true
    }
}
