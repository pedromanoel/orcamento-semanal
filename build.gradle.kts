import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
import org.gradle.api.tasks.testing.logging.TestLogEvent.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.70" apply false
}

subprojects {
    repositories {
        jcenter()
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        systemProperties(
                "junit.jupiter.testinstance.lifecycle.default" to "per_class"
        )
        testLogging {
            events(PASSED, SKIPPED, FAILED)
            exceptionFormat = FULL
        }
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "13"
            languageVersion = "1.3"
        }
    }
}