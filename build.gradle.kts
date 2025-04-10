plugins {
    `maven-publish`
    kotlin("jvm") version "2.0.21"
    id("java-library")
    id("com.google.devtools.ksp") version "2.0.21-1.0.28"
}

java {
    withJavadocJar()
    withSourcesJar()
}

group = "com.github.sloimayyy"
version = "1.0.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    //testImplementation(kotlin("test"))
    ksp("com.google.devtools.ksp:symbol-processing-api:2.0.21-1.0.28")
    ksp(project(":vectorprocessor"))
}

publishing {
    publications {
        // Quick and dirty maven local for quick dev iterations
        create<MavenPublication>("maven") {
            groupId = "com.sloimay"
            artifactId = "smathdev"
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

