plugins {
    kotlin("jvm") version "1.9.23"
    // https://kotlinlang.org/docs/no-arg-plugin.html#jpa-support
    kotlin("plugin.jpa") version "2.1.0"
    // https://kotlinlang.org/docs/all-open-plugin.html#spring-support
    id("org.jetbrains.kotlin.plugin.spring") version "2.1.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager
    implementation("org.hibernate:hibernate-entitymanager:5.6.15.Final")
    runtimeOnly("com.h2database:h2:2.2.224")

    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}