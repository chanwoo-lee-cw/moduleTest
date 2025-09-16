package com.example.api.controller

import com.example.api.application.HelloApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/")
class IndexController(
    private val helloApplication: HelloApplication
) {

    @GetMapping("/hello")
    fun hello(): String {
        return helloApplication.hello()
    }
}