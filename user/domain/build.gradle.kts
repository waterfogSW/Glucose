import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

plugins {
    `java-test-fixtures`
}

dependencies {
    implementation(project(":common:ulid"))
}
