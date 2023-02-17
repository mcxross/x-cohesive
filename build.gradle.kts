plugins {
    kotlin("jvm") version "1.8.0"
    id("org.jetbrains.compose")
    id("com.google.devtools.ksp") version "1.8.0-1.0.9"
}

group = "com.mcxross.xcohesive"
version = "0.1.0"

repositories {
    mavenCentral()
}

dependencies {
    dependencies {
        compileOnly(kotlin("stdlib"))
        //TODO: To be replaced with the following line
       /* compileOnly(project(":cmpe:common"))
        compileOnly(project("::cmpe:csp"))
        ksp(project(":cmpe:csp"))*/
    }
    testImplementation(kotlin("test"))
}