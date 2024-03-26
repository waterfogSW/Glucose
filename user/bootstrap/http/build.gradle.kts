dependencies {
    implementation(project(":support:common"))

    implementation(project(":user:domain"))
    implementation(project(":user:application"))
    implementation(project(":user:infrastructure:client"))
    implementation(project(":user:infrastructure:persistence"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.springdoc.openapi.starter.webmvc.ui)

    developmentOnly(rootProject.libs.spring.boot)
    developmentOnly(rootProject.libs.spring.boot.docker.compose)
}
