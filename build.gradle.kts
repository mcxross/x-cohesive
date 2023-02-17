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

dependencies {
  dependencies {
    compileOnly(kotlin("stdlib"))
    compileOnly("com.mcxross.cohesive:cohesive-desktop:0.1.0")
    compileOnly("com.mcxross.cohesive:cohesive-csp:0.1.0")
    ksp("com.mcxross.cohesive.csp:cohesive-csp:0.1.0")
  }
  testImplementation(kotlin("test"))
}
