plugins {
    id("org.jetbrains.kotlin.jvm")

    application
}

dependencies {
    implementation(project(":domain"))

    implementation("com.natpryce:konfig:1.6.10.0")
    implementation("io.javalin:javalin:3.7.0")
    implementation("io.pebbletemplates:pebble:3.1.2")
    implementation("org.slf4j:slf4j-simple:1.7.28")
    implementation("org.koin:koin-core:2.1.5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")

    // test & asserts
    testImplementation(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.22")
    // mocks
    testImplementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    testImplementation("org.mockito:mockito-core:3.3.3")
    // rest client
    testImplementation("com.konghq:unirest-java:3.7.00")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}

application {
    mainClassName = "codes.pedromanoel.orcamento.app.MainKt"
}
