plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "2.0.21"
    application
}

group = "fr.valentin.dockot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("reflect"))

    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC2")

    implementation("me.tongfei:progressbar:0.10.0")

    implementation("org.mongodb:mongodb-driver-kotlin-coroutine:4.11.0")
    implementation("org.mongodb:bson-kotlin:4.11.0")

    implementation("org.apache.commons:commons-csv:1.10.0")

    implementation("com.iknova.gl.core:gl-core-common-jvm:0.4")
    implementation("com.iknova.gl.structdata4:gl-structdata4-core:4.2.7")

    implementation("net.sf.ucanaccess:ucanaccess:5.0.1")

    implementation("org.reflections:reflections:0.10.2")

    testImplementation(kotlin("test"))
}

repositories {
    maven {
        name = "Iknova Artifactory"
        url = uri("https://repo.iknova.corp/artifactory/all-releases/")
    }
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("MainKt")
}
