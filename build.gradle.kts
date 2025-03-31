

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
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/sloimayyy/mcvolume")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("GITHUB_USERNAME")
                password = project.findProperty("gpr.key") as String? ?: System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register<MavenPublication>("gpr") {
            from(components["java"])

            // Optional: customize POM information
            pom {
                name.set("Your Library Name")
                description.set("A description of your library")
                url.set("https://github.com/yourusername/yourrepository")

                licenses {
                    license {
                        name.set("The License Name") // e.g., "MIT", "Apache 2.0"
                        url.set("LICENSE URL")
                    }
                }

                developers {
                    developer {
                        id.set("yourusername")
                        name.set("Your Name")
                        email.set("your.email@example.com")
                    }
                }
            }
        }
    }

    repositories {
        mavenLocal()
    }
}


