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

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("io.mockk:mockk:1.9.3")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
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
