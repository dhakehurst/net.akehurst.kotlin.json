plugins {
    id("net.akehurst.kotlin.kt2ts") version "1.5.0"
}

val version_kotlinx:String by project

dependencies {
    commonMainImplementation("net.akehurst.kotlinx:kotlinx-collections:$version_kotlinx")
}


kt2ts {
    jvmTargetName.set("jvm8")

    classPatterns.set(listOf(
            "net.akehurst.kotlin.json.*"
    ))
}
