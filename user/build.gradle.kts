import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version user.Version.SPRING_BOOT
    id("io.spring.dependency-management") version user.Version.SPRING_BOOT_DEPENDENCY_MANAGEMENT
    kotlin("jvm") version user.Version.KOTLIN
    kotlin("plugin.jpa") version user.Version.KOTLIN
    kotlin("plugin.spring") version user.Version.KOTLIN
}

allprojects {
    group = "com.waterfogsw"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "idea")
    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")
    apply(plugin = "kotlin-kapt")
    apply(plugin = "kotlin-noarg")
    apply(plugin = "kotlin-allopen")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-stdlib:${user.Version.KOTLIN}")
        implementation("org.jetbrains.kotlin:kotlin-reflect:${user.Version.KOTLIN}")
        annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:${user.Version.SPRING_BOOT}")
        testImplementation("io.mockk:mockk:${user.Version.MOCKK}")
        testImplementation("com.ninja-squad:springmockk:${user.Version.SPRINGMOCKK}")
        testImplementation("io.kotest:kotest-runner-junit5:${user.Version.KOTEST}")
        testImplementation("io.kotest:kotest-assertions-core:${user.Version.KOTEST}")
        testImplementation("io.kotest.extensions:kotest-extensions-spring:${user.Version.KOTEST_EXTENSIONS_SPRING}")
        testImplementation("org.springframework.boot:spring-boot-starter-test:${user.Version.SPRING_BOOT}")
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(user.Version.JAVA))
        }
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs += "-Xjsr305=strict"
            jvmTarget = user.Version.JAVA
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

}
