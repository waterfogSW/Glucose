dependencies {
    implementation(project(":support:common"))
    implementation(project(":domain"))
    implementation(project(":application"))

    implementation(libs.spring.boot.starter.data.jpa)
    runtimeOnly(libs.mysql.connector.java)
}
