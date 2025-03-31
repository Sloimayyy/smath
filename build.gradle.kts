plugins {
    `maven-publish`
    kotlin("jvm") version "1.6.10"
    id("java-library")
}

java {
    withJavadocJar()
    withSourcesJar()
}

group = "com.github.sloimayyy"
version = "1.0.4"

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
            groupId = project.group as String
            artifactId = "smath"
            version = project.version as String

            from(components["java"])
        }
    }

    /*repositories {
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
    }*/
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])

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
}

