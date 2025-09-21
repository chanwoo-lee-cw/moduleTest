# Kotlin Spring에서 멀티 모듈 프로젝트 생성하기

## 시작하며

프로젝트를 개발하다보면 프로젝트를 여러 모듈로 쪼개서 관리를 해야할 필요성이 있을때가 있다. 
예를 들면, Git에서는 Submodule이라는 기능이 있는데, 한 서비스에서 공통 부분을 다른 서비스에서 공유하고 설정하기 편해지는 장점이 있다.
그래서, 이런 식으로 다양한 모듈을 쪼개서 사용함을써 간편히 기능을 공유하는 케이스에 대해 확인해보자고 한다.

## Spring Module이란?
> Java에서 멀티 모듈이란 하나의 루트 프로젝트 안에서 여러 개의 하위 모듈을 생성하여, 각 모듈이 독립적으로 빌드·테스트·관리되면서도 서로 간의 의존할 수 있도록 관계를 가지는 형태를 말한다.

요컨데, 프로젝트를 기능 별로 쪼개서 각자 빌드하거나 코드를 재사용할 수 있게 관계를 만드는 것을 말한다.

## 멀티 모듈의 장단점
### 장점

- 코드의 재사용성 향상 : 공통적으로 사용되는 모듈은 다른 모듈에서 손쉽게 재사용이 가능하고, 변경 시 일괄적으로 변경이 가능해진다.
- 분리된 관심사 관리 : 각각의 모듈은 독립적이고, 각 모듈이 각자의 책임을 가지게 되어서 유지보수가 쉬워진다.
- 빌드 효율성 향상 : 프로젝트가 커지는 경우에 전체를 빌드하는 대신 변경된 모듈만 빌드함으로써 빌드 시간이 단축된다.
- 협업 효율성 향상 : 각각의 모듈을 팀원들이 독립적으로 개발할 수 있어서 효율성이 향상된다.
- 의존성 관리 개선 : 각자가 필요한 의존성만 가져감으로써 의존성이 복잡하게 꼬이는 것을 막을 수 있고, 버전 관리도 편해진다.


### 단점

- 복잡성 증가: 멀티 모듈 구조는 설정과 관리가 다소 복잡하여 작은 프로젝트에는 적합하지 않을 수 있다.
- 모듈 구성의 어려움 : 어디서 모듈을 나눌 것인지, 어떤 책임을 나눠가질 것에 대해 많은 고민이 필요하다
- 빌드 구성 관리: 각 모듈의 build.gradle 파일을 개별 관리해야 하므로 설정에 신경 써야 할 부분이 늘어납니다.
- 테스트의 어려움 : 각 모듈은 각자가 다른 프로젝트이고, 각자 다른 설정을 가지고 있기 때문에 테스트를 구성할 때도 설정이 복잡해진다


## Spring에서 멀티 모듈 프로젝트를 생성하는 방법

> 실습 환경
> IDE : InteliJ IDEA
> 언어 : Kotlin
> Java 17
> Build: Gradle-Kotlin

### Example

[https://github.com/chanwoo-lee-cw/moduleTest](https://github.com/chanwoo-lee-cw/moduleTest)


### 프로젝트 구상


![](https://velog.velcdn.com/images/alphanewbie/post/3825fef9-ca63-47d4-bc80-028f6dc9348a/image.png)

위와 같은 구조의 프로젝트를 구현해볼 생각이다.

- api
  - Spring Boot 실행 부분
  - Controller, Aplication, Request/Response Model 작성
- common
  - 공통 기능 작성
  - ErrorHandler, ResponssFormat, 각종 Util 기능
- domain
  - DB와 데이터를 처리하기 위한 로직
  - Entity, Repository, Service, Dto 작성


### 요청의 처리 흐름

```
Controller ------- Application ------- Service ------- Repository
            model                dto            entity
```




### 기본 설정


1. 프로젝트를 생성한다.

![](https://velog.velcdn.com/images/alphanewbie/post/4c6d770f-e032-4dfd-a634-63dd4cfa5141/image.png)

Build Type은 Gradle-Kotlin으로 지정한다.

2. src 폴더를 삭제한다

![](https://velog.velcdn.com/images/alphanewbie/post/ed9e2a19-b958-4539-b949-e31d0484a430/image.png)

3.  root 프로젝트의 `build.gradle.kts`를 수정한다

Repository를 모든 프로젝트에 적용 하도록 변경한다.
```
// 수정된 Repository
allprojects {
    repositories {
        mavenCentral()
    }
}
```

dependencies와 tasks를 서브 프로젝트에 적용하도록 변경한다.
```
// 수정된 dependencies
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
```


- 수정된 Gradle 파일

```gradle
plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.5.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "spring-module-test"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

```

4. 모듈을 생성한다
![](https://velog.velcdn.com/images/alphanewbie/post/528edbd9-a371-48f2-a0bc-809329757f0a/image.png)

![](https://velog.velcdn.com/images/alphanewbie/post/1e89a033-f1bb-4dd3-a6d9-8ca871d6ceab/image.png)

생성 후에 필요없는 파일을 지워준다.
만약 domain, common 같이 실행을 시키지 않는 모듈이라면 `@SpringBootApplication` 파일도 삭제한다

![](https://velog.velcdn.com/images/alphanewbie/post/0866ff44-40d5-4280-896e-431ffdab31c5/image.png)


5. 루트 모듈의 `settings.gradle.kts` 파일에 모듈을 등록한다.

프로젝트가 시작할 때, 루트 모듈의 하위에 어떤 모듈이 있는지 알려주기 위해 등록한다.

```
// settings.gradle.kts
rootProject.name = "spring-module-test"
include("api", "domain", "common")
```




6. 모듈의 Gradle을 수정한다.

```
tasks.register("prepareKotlinBuildScriptModel") {}
```
해당 옵션이 무슨 기능인지는 자세히는 모르겠지만, `KotlinDslScriptsModel`이 root 모듈에서만 빌드가 되기 때문에, 하위의 서브 모듈들의 task가 없으면 에러가 나기 때문에 task를 등록해주는 역할로 필요한걸로 추정된다.


- api

Spring boot 서비스를 구성하기 위한 기본적인 의존성과 사용할 서브 모듈들, 즉 domian과 common 모듈을 추가한다.
DB의 의존성도 같이 추가해주는데 Spring Boot가 DB 커넥션 풀을 생성하는 시점음 API 실행 시점이기 때문이다. 물론 DB 환경 변수도 `api`모듈에 추가해줘야 한다.

```
// api/build.gradle.kts
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
```

- domain

Jpa를 사용하기 때문에 JPA 의존성을 추가한다.

```
// domain/build.gradle.kts
plugins {
    kotlin("plugin.jpa")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "domain"
val queryDslVersion = "5.1.0"


dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    // JPA
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

```

- common

HttpResponseFormat을 정의하고, `@RestControllerAdvice`를 이용한 에러 핸들러를 생성할 예정이기 때문에 spring-boot-web을 추가해준다.

```
// common/build.gradle.kts
plugins {
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
description = "common"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
}

tasks.register("prepareKotlinBuildScriptModel") {}

```

7. Bean 등록

api 모듈에서 컴포넌트 스캔을 통해 하위 모듈의 Bean을 등록해준다.

```kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan


@ComponentScan(
    basePackages = [
        "com.example.api",
        "com.example.domain",
    ]
)
@SpringBootApplication
class ApiApplication

fun main(args: Array<String>) {
    runApplication<ApiApplication>(*args)
}
```

8. DB Entity 설정 등록

```kotlin
// com/example/domain/common/config/JpaConfig.kt

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement


@EntityScan(basePackages = ["com.example.domain.entity"])
@EnableJpaRepositories(basePackages = ["com.example.domain.repository"])
@EnableTransactionManagement
@Configuration
class JpaConfig {}
```

실행 모듈이 `api`이고, 엔티티와 레포지토리는 다른 모듈에 들어가 있기 때문에 해당 Entity와 Reposity를 찾기 못하기 때문에 해당 엔티티와 레포지토리를 어디서 찾아야 하는지 알려주는 설정이다.



## 참고 문헌
- [https://velog.io/@jthugg/spring-multi-module](https://velog.io/@jthugg/spring-multi-module)
- [https://shs00925.tistory.com/419](https://shs00925.tistory.com/419)
- [https://cofls6581.tistory.com/274](https://cofls6581.tistory.com/274)
- [https://github.com/gradle/gradle/issues/14889](https://github.com/gradle/gradle/issues/14889)
- [https://docs.gradle.org/current/kotlin-dsl/gradle/org.gradle.tooling.model.kotlin.dsl/-kotlin-dsl-scripts-model/index.html](https://docs.gradle.org/current/kotlin-dsl/gradle/org.gradle.tooling.model.kotlin.dsl/-kotlin-dsl-scripts-model/index.html)
