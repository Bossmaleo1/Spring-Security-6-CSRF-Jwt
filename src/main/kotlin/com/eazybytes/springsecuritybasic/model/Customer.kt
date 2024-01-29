package com.eazybytes.springsecuritybasic.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*
import org.hibernate.annotations.GenericGenerator


@Entity
class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column(name = "customer_id")
    var id: Int = 0

    var name: String? = null

    var email: String? = null

    @Column(name = "mobile_number")
    var mobileNumber: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var pwd: String? = null

    var role: String? = null

    @Column(name = "create_dt")
    var createDt: String? = null

    @JsonIgnore
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    var authorities: Set<Authority>? = null

}