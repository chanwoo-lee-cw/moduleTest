package com.example.domain.service

import com.example.domain.dto.order.OrderDto
import com.example.domain.repository.order.OrderRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository
) {

    /**
     없으면 EntityNotFoundException 예외 발생
     */
    fun getEntityById(orderId: Long): OrderDto {
        val entity = orderRepository.findByIdOrNull(orderId) ?: throw EntityNotFoundException()
        return OrderDto.Companion.from(entity)
    }


}