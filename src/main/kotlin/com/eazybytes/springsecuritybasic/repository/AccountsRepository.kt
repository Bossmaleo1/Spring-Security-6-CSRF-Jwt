package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Accounts
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountsRepository : CrudRepository<Accounts?, Long?> {
    fun findByCustomerId(customerId: Int): Accounts?
}