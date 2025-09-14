plugins {
    kotlin("plugin.jpa")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "domain"


dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.register("prepareKotlinBuildScriptModel") {}