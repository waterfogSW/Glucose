import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":common:jwt"))
    implementation(project(":common:support"))

    implementation(project(":user:domain"))
    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot:${Version.SPRING_BOOT}")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign:${Version.OPENFEIGN}")

    testFixturesImplementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT}")
    testFixturesImplementation("org.springframework.cloud:spring-cloud-starter-contract-stub-runner:${Version.SPRING_COULD_CONTRACT}")
    testFixturesImplementation("org.springframework.boot:spring-boot-starter-test:${Version.SPRING_BOOT}")
}
