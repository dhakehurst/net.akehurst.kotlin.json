plugins {
    id("net.akehurst.kotlin.kt2ts") version "1.0.0"
}

val version_kotlinx:String by project

dependencies {
    commonMainImplementation("net.akehurst.kotlinx:kotlinx-collections:$version_kotlinx")
}


val tsdDir ="${buildDir}/tmp/jsJar/ts"

kotlin {
    sourceSets {
        val jsMain by getting {
            resources.srcDir("${tsdDir}")
        }
    }
}

kt2ts {
    localJvmName.set("jvm8")
    modulesConfigurationName.set("jvm8RuntimeClasspath")
    outputDirectory.set(file("${tsdDir}"))
    classPatterns.set(listOf(
            "net.akehurst.kotlin.json.*"
    ))
}
tasks.getByName("generateTypescriptDefinitionFile").dependsOn("jvm8MainClasses")
tasks.getByName("jsJar").dependsOn("generateTypescriptDefinitionFile")