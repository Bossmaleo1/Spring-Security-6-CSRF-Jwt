package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Customer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


@Repository
interface CustomerRepository : CrudRepository<Customer, Long> {

    fun findByEmail(email: String): List<Customer>

}