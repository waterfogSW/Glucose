dependencies {
    implementation(project(":user:domain"))
    implementation(project(":user:application"))
    implementation(project(":user:infrastructure:client"))

    implementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT}")
}
