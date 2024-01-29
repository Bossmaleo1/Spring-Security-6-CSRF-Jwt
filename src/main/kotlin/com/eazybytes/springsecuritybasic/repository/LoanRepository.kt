package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.Loans
import org.springframework.data.repository.CrudRepository
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository : CrudRepository<Loans?, Long?> {

    @PreAuthorize("hasRole('USER')")
    fun findByCustomerIdOrderByStartDtDesc(customerId: Int): List<Loans?>?


}