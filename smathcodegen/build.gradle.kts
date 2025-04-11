plugins {
    kotlin("jvm")
}

group = "com.sloimay"
version = "1.0.5"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

/*tasks.register<JavaExec>("generateCode") {
    group = "smath"
    description = "Generate code for smath"

    mainClass.set("com.sloimay.smathcodegen.MainKt")
    classpath = sourceSets["main"].runtimeClasspath
    args = listOf()

    // Makes sure Gradle waits for the end of the execution
    // of the process
    isIgnoreExitValue = false
}

tasks.getByName("build").dependsOn("generateCode")*/


tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}