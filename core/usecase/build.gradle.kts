dependencies {
    implementation(project(":core:domain"))
    implementation(project(":utils"))

    testImplementation(kotlin("test"))
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.0")
    testImplementation("io.mockk:mockk:1.12.3")
    testImplementation("com.google.truth:truth:1.1.3")
}
