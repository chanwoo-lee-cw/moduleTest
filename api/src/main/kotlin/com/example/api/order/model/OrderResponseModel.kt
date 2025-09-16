package com.example.api.order.model

import com.example.domain.dto.order.OrderDto
import java.time.Instant


data class OrderDetailResponse(
    val id: Long,
    val orderItem: String,
    val price: Long,
    val orderDate: Instant,
) {
    companion object {
        fun from(orderDto: OrderDto): OrderDetailResponse {
            with(orderDto) {
                return OrderDetailResponse(
                    id = id,
                    orderItem = orderItem,
                    price = price,
                    orderDate = orderDate,
                )
            }
        }
    }
}