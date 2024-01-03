import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true


dependencies {
    implementation(project(":common:ulid"))
    implementation(project(":user:domain"))

    implementation("org.springframework.boot:spring-boot:${Version.SPRING_BOOT}")
}
