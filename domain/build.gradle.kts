plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("org.assertj:assertj-core:3.15.0")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
