
dependencies {
    implementation(project(":common:support"))
    implementation(project(":user:domain"))
    implementation(project(":user:application"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:${Version.SPRING_BOOT}")

    runtimeOnly("mysql:mysql-connector-java:${Version.MYSQL}")

    testRuntimeOnly("com.h2database:h2:${Version.H2}")
}
