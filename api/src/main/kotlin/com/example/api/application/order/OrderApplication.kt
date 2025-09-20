package com.example.api.application.order

import com.example.api.model.order.OrderDetailResponse
import com.example.domain.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderApplication(
    private val orderService: OrderService
) {

    fun findDetails(orderId: Long) =
        OrderDetailResponse.Companion.from(
            orderService.getEntityById(orderId)
        )
}