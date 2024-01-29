package com.eazybytes.springsecuritybasic.model

import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
@Table(name = "authorities")
class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    var id: Long? = null

    var name: String? = null

    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: Customer? = null
}