import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":user:domain"))
    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot:${Version.SPRING_BOOT}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Version.OPENFEIGN}")
}
