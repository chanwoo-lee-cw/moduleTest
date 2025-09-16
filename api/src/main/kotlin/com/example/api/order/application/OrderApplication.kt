package com.example.api.order.application

import com.example.api.order.model.OrderDetailResponse
import com.example.domain.service.OrderService
import org.springframework.stereotype.Service

@Service
class OrderApplication(
    private val orderService: OrderService
) {

    fun findDetails(orderId: Long) =
        OrderDetailResponse.from(
            orderService.getEntityById(orderId)
        )
}