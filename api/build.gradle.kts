plugins {
    id("org.springframework.boot") version "3.5.5"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "api"


dependencies {
    implementation(project(":domain"))
    implementation(project(":common"))
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // db
    runtimeOnly("com.mysql:mysql-connector-j:9.0.0")
}

tasks.register("prepareKotlinBuildScriptModel") {}