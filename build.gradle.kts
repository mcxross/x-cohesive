plugins {
  kotlin("jvm") version "1.8.0"
  id("org.jetbrains.compose")
  id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

group = "com.mcxross.xcohesive"
version = "0.1.0"

repositories {
  mavenCentral()
  mavenLocal()
}

val pluginId by extra { "com.mcxross.xcohesive" }
val pluginDescription by extra { "Cohesive secondary plugin template" }
val pluginRequires by extra { "2021.2" }
val pluginClass by extra { "com.mcxross.xcohesive.CohesivePlugin" }
val pluginProvider by extra { "McXross" }
val pluginDependencies by extra { "" }
val pluginLicense by extra { "Apache 2.0" }
val pluginVersion by extra { "0.1.0" }

val project = this

tasks.register<Jar>("plugin") {
  archiveBaseName.set(pluginId)

  into("classes") { with(tasks.named<Jar>("jar").get()) }

  dependsOn(configurations.runtimeClasspath)
  into("lib") {
    from({ configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") } })
  }
  archiveExtension.set("zip")
}

tasks.register<Copy>("assemblePlugin") {
  from(project.tasks.named("plugin"))
  into(file("$projectDir/build/plugin"))
}

tasks.named<Jar>("jar") {
  manifest {
    attributes["Plugin-Version"] = archiveVersion
    attributes["Plugin-Id"] = pluginId
    attributes["Plugin-Description"] = pluginDescription
    attributes["Plugin-Class"] = pluginClass
    attributes["Plugin-Dependencies"] = pluginDependencies
    attributes["Plugin-Requires"] = pluginRequires
    attributes["Plugin-Provider"] = pluginProvider
    attributes["Plugin-License"] = pluginLicense
  }
}

tasks.named("build") { dependsOn(tasks.named("plugin")) }

tasks.register<Copy>("assemblePlugins") {
  dependsOn(tasks.named("assemblePlugin"))
}

tasks { "build" { dependsOn(named("assemblePlugins")) } }

dependencies {
  dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.mcxross.cohesive:cohesive-desktop:0.1.0")
    compileOnly("com.mcxross.cohesive:cohesive-csp:0.1.0")
    ksp("com.mcxross.cohesive:cohesive-csp:0.1.0")
  }
  testImplementation(kotlin("test"))
}
