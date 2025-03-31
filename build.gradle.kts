plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
    id("maven-publish")
    id("java-library")
}

java {
    withJavadocJar()
    withSourcesJar()
}

group = "com.sloimay"
version = "1.0.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    //testImplementation(kotlin("test"))
}

publishing {
    /*publications {
        create<MavenPublication>("maven") {
            groupId = "com.sloimay"
            artifactId = "smath"
            version = "1.0.0"

            from(components["java"])
        }
    }*/

    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/Sloimayyy/smath")
            credentials {
                val (un, key) = file("private/publishing_creds.txt")
                        .readLines()
                        .map { it.trim() }
                username = un
                password = key
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])

            // Optional: customize POM information
            pom {
                name.set("smath")
                description.set("Sloimay's Kotlin Math Library")
                url.set("https://github.com/Sloimayyy/smath")

                licenses {
                    license {
                        name.set("Apache 2.0")
                        url.set("https://www.apache.org/licenses/LICENSE-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("sloimayyy")
                        name.set("Sloimay")
                        email.set("sloimayyy@gmail.com")
                    }
                }
            }
        }
    }

    repositories {
        mavenLocal()
    }
}


