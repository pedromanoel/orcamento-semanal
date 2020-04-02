plugins {
    id("org.jetbrains.kotlin.jvm")

    application
}

dependencies {
    implementation("com.natpryce:konfig:1.6.10.0")
    implementation("io.javalin:javalin:3.7.0")
    implementation("io.pebbletemplates:pebble:3.1.2")
    implementation("org.slf4j:slf4j-simple:1.7.28")
    implementation(project(":domain"))

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("com.konghq:unirest-java:3.7.00")
    testImplementation("com.thoughtworks.gauge:gauge-java:0.7.4")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClassName = "codes.pedromanoel.AppKt"
}
