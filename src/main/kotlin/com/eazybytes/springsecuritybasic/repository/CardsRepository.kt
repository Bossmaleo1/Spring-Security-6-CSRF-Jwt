package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Cards
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CardsRepository : CrudRepository<Cards?, Long?> {
    fun findByCustomerId(customerId: Int): List<Cards?>?
}