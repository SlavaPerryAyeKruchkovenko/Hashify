plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.hashify"
version = "1.0-SNAPSHOT"
val projectName: String = rootProject.name

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation(kotlin("reflect"))
    implementation(kotlin("stdlib"))
}
application {
    mainClass.set("org.hashify.MainKt")
    applicationDefaultJvmArgs = listOf("-Dproject.name=${rootProject.name}")
}
tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
