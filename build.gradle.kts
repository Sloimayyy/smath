plugins {
    `maven-publish`
    kotlin("jvm") version "2.0.21"
    id("java-library")
    idea
    //id("com.google.devtools.ksp") version "2.0.21-1.0.28"
}

java {
    withJavadocJar()
    withSourcesJar()
}

group = "com.github.sloimayyy"
version = "1.1.1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    //ksp("com.google.devtools.ksp:symbol-processing-api:2.0.21-1.0.28")
    //implementation(project(":vectorprocessor"))
    //ksp(project(":vectorprocessor"))
    //implementation(project(":vectorannotations"))
    //implementation(project(":vectorgenerated"))
    //testImplementation(kotlin("test"))

    //implementation(project(":smathcodegen"))
}



/*tasks.register<JavaExec>("generateCodeInSmathCodegen") {
    group = "smath"
    description = "Generate code files by running smathcodegen"

    // Ensure smathcodegen is built and code generation is triggered
    dependsOn(":smathcodegen:build", ":smathcodegen:generateCode")

    // Use the main class for the code generation task in smathcodegen
    mainClass.set("com.sloimay.smathcodegen.MainKt") // Adjust to your actual class
    classpath = project(":smathcodegen").sourceSets["main"].runtimeClasspath
    args = listOf() // Optional arguments for your code generation
}

tasks.getByName("build").dependsOn("generateCodeInSmathCodegen")*/

/*tasks.register("waitForCodeGen") {
    Thread.sleep(5_000)
}*/

//tasks.getByName("build").dependsOn("waitForCodeGen")


/*sourceSets.main {
    kotlin.srcDirs("build/generated/ksp/main/kotlin")
}*/

/*tasks.compileKotlin {
    dependsOn("kspKotlin")
}*/

// Make the build task depend on our new copy task
/*tasks.named("build") {
    dependsOn("GenerateVec5")
}

tasks.register<Copy>("GenerateVec5") {

    val s = "package com.sloimay.smath.vectors\n\ndata class Vec5(val x: Float, val y: Float, val z: Float, val w: Float, val v: Float)"

    val f = File("""F:\Intellij Workspaces\sloi_math_lib\src\main\kotlin\smath\vectors\Vec5.kt""")
    f.writeText(s)

}*/



publishing {
    /*publications {
        // Quick and dirty maven local for quick dev iterations
        create<MavenPublication>("maven") {
            groupId = "com.sloimay"
            artifactId = "smathdev"
            version = project.version as String

            from(components["java"])
        }
    }*/

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

