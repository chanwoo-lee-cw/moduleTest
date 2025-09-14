plugins {
    id("org.springframework.boot") version "3.5.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "api"


dependencies {
    implementation(project(":domain"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.register("prepareKotlinBuildScriptModel") {}