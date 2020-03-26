plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.70"

    application
}

repositories {
    jcenter()
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.natpryce:konfig:1.6.10.0")
    implementation("io.javalin:javalin:3.7.0")
    implementation("org.slf4j:slf4j-simple:1.7.28")

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
    testImplementation("com.konghq:unirest-java:3.7.00")
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
