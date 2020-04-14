plugins {
    id("org.jetbrains.kotlin.jvm")
}

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation(kotlin("stdlib-jdk8"))

    testImplementation("com.willowtreeapps.assertk:assertk:0.22")
    testImplementation("org.junit.jupiter:junit-jupiter:5.6.1")
}