import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-core:4.5.1")
    implementation("io.vertx:vertx-web:4.5.1")
    implementation("io.vertx:vertx-lang-kotlin:4.5.1")
    implementation("io.vertx:vertx-lang-kotlin-coroutines:4.5.1")
    implementation("io.vertx:vertx-jdbc-client:4.5.1")
    implementation("mysql:mysql-connector-java:8.0.33")

    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    implementation("com.google.code.gson:gson:2.10.1")

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaExec> {
    mainClass.set("com.ryanbaskara.sample.MainKt")
}

application {
    mainClass.set("com.ryanbaskara.sample.MainKt")
}
