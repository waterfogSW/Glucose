import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true


dependencies {
    implementation(project(":support:jwt"))
    implementation(project(":support:common"))
    implementation(project(":user:domain"))

    implementation(rootProject.libs.spring.boot)
    testImplementation(testFixtures(project(":user:domain")))
}
