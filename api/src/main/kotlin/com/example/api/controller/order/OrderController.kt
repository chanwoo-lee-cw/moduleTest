package com.example.api.controller.order

import com.example.api.application.order.OrderApplication
import com.example.api.model.order.OrderDetailResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/orders")
class OrderController(
    private val orderApplication: OrderApplication
) {

    @GetMapping("/{id}")
    fun findDetails(
        @PathVariable id: Long
    ): ResponseEntity<OrderDetailResponse> =
        ResponseEntity.ok(
            orderApplication.findDetails(id)
        )
}