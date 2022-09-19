plugins {
    id("org.jetbrains.kotlin.jvm") version "1.7.10"
    application
    id ("com.github.johnrengelman.shadow") version "7.1.2"
}

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.github.ceclin:jrmp:v0.2.0")
    implementation("com.github.frohoff:ysoserial:v0.0.6") {
        exclude(group = "org.jenkins-ci.main")
        exclude(group = "javax.interceptor")
    }
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

application {
    mainClass.set("soln.AppKt")
}

tasks.shadowJar {
    minimize()
}
