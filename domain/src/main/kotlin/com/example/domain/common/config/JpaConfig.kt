package com.example.domain.common.config

import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@EntityScan(basePackages = ["com.example.domain.entity"])
@EnableJpaRepositories(basePackages = ["com.example.domain.repository"])
@EnableTransactionManagement
@Configuration
class JpaConfig {

}