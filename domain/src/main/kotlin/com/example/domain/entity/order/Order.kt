package com.example.domain.entity.order

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.Instant

@Entity
@Table(name = "orders")
data class Order(
    @Column(name = "order_item", nullable = false)
    val orderItem: String,

    @Column(name = "price", nullable = false)
    val price: Long,

    @Column(name = "order_date", nullable = false)
    val orderDate: Instant,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
)