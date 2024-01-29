package com.eazybytes.springsecuritybasic.repository

import com.eazybytes.springsecuritybasic.model.AccountTransactions
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountTransactionsRepository : CrudRepository<AccountTransactions?, Long?> {
    fun findByCustomerIdOrderByTransactionDtDesc(customerId: Int): List<AccountTransactions?>?
}