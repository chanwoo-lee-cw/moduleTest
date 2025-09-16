package com.example.api.application

import com.example.domain.service.HelloService
import org.springframework.stereotype.Service

@Service
class HelloApplication(
    private val helloService: HelloService
) {

    fun hello() = helloService.hello()

}