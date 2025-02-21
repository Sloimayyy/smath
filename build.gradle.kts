

plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
}

group = "com.sloimay"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    //testImplementation(kotlin("test"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.sloimay"
            artifactId = "smath"
            version = "1.0.0"

            from(components["java"])
        }
    }
    repositories {
        mavenLocal()
    }
}


