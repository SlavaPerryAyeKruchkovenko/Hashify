plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.hashify"
version = "0.0.1"
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
tasks.jar {
    archiveFileName.set("${projectName}.jar")
    manifest {
        attributes(
            "Main-Class" to application.mainClass.get() // Указываем главный класс
        )
    }
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}
kotlin {
    jvmToolchain(17)
}
