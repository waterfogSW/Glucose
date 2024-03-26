import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":support:jwt"))
    implementation(project(":support:common"))

    implementation(project(":user:domain"))
    implementation(project(":user:application"))

    implementation(rootProject.libs.spring.boot)
    implementation(rootProject.libs.spring.cloud.starter.openfeign)

    testFixturesImplementation(rootProject.libs.spring.boot.starter.web)
    testFixturesImplementation(rootProject.libs.spring.cloud.starter.contract.stub.runner)
    testFixturesImplementation(rootProject.libs.spring.boot.starter.test)
}
