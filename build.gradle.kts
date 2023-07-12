val mod_id: String by extra
val mod_version: String by extra
val xmlutil_version: String by extra

plugins {
    idea
    id("com.possible_triangle.gradle") version ("0.0.0-dev")
}

withKotlin()

mod {
    includedLibraries.set(setOf(
        "io.github.pdvrieze.xmlutil:core-jvm:${xmlutil_version}",
        "io.github.pdvrieze.xmlutil:serialization-jvm:${xmlutil_version}",
    ))
}

subprojects {
    repositories {
        modrinthMaven()
        curseMaven()

        maven {
            url = uri("https://maven.theillusivec4.top/")
            content {
                includeGroup("top.theillusivec4.curios")
            }
        }

        maven {
            url = uri("https://maven.blamejared.com")
            content {
                includeGroup("vazkii.botania")
            }
        }
    }

    enablePublishing {
        repositories {
            githubPackages(this@subprojects)
        }
    }

    tasks.withType<Jar> {
        exclude("datapacks")
    }
}

enableSonarQube()

idea {
    module.excludeDirs.add(file("web"))
    module.excludeDirs.add(file("datagen"))
}