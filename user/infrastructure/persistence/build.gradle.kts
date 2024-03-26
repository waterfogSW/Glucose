dependencies {
    implementation(project(":support:common"))
    implementation(project(":user:domain"))
    implementation(project(":user:application"))

    implementation(rootProject.libs.spring.boot.starter.data.jpa)

    runtimeOnly(rootProject.libs.mysql.connector.java)
    testRuntimeOnly(rootProject.libs.h2)
}
