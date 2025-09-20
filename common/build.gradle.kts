plugins {
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "common"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")

    // lombok
    implementation("org.projectlombok:lombok")
}

tasks.register("prepareKotlinBuildScriptModel") {}