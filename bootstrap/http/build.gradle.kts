dependencies {
    implementation(project(":support:common"))

    implementation(project(":domain"))
    implementation(project(":application"))
    implementation(project(":infrastructure:client"))
    implementation(project(":infrastructure:persistence"))
    implementation(project(":infrastructure:redis"))

    implementation(rootProject.libs.spring.boot.starter.web)
    implementation(rootProject.libs.spring.boot.starter.websocket)
    implementation(rootProject.libs.springdoc.openapi.starter.webmvc.ui)

    developmentOnly(rootProject.libs.spring.boot)
    developmentOnly(rootProject.libs.spring.boot.docker.compose)
}
