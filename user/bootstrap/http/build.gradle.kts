dependencies {
    implementation(project(":user:domain"))
    implementation(project(":user:application"))
    implementation(project(":user:infrastructure:client"))
    implementation(project(":user:infrastructure:persistence"))

    implementation("org.springframework.boot:spring-boot-starter-web:${Version.SPRING_BOOT}")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:${Version.SPRINGDOC_OPENAPI}")

    developmentOnly("org.springframework.boot:spring-boot-devtools:${Version.SPRING_BOOT}")
    developmentOnly("org.springframework.boot:spring-boot-docker-compose:${Version.SPRING_BOOT}")
}
