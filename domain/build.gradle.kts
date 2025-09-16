plugins {
    kotlin("plugin.jpa")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "domain"
val queryDslVersion = "5.1.0"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.querydsl:querydsl-jpa:${queryDslVersion}:jakarta")
    implementation("com.querydsl:querydsl-core:${queryDslVersion}")
    kapt("com.querydsl:querydsl-apt:${queryDslVersion}:jakarta")
}

tasks.register("prepareKotlinBuildScriptModel") {}

val querydslSrcDir = "src/main/generated"

tasks {
    clean {
        delete(files(querydslSrcDir))
    }
}
