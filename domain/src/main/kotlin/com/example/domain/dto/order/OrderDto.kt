package com.example.domain.dto.order

import com.example.domain.entity.order.Order
import java.time.Instant

data class OrderDto(
    val id: Long,
    val orderItem: String,
    val price: Long,
    val orderDate: Instant,
) {
    companion object {
        fun from(entity: Order): OrderDto {
            with(entity) {
                return OrderDto(
                    id = id,
                    orderItem = orderItem,
                    price = price,
                    orderDate = orderDate,
                )
            }
        }
    }
}