package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.AccountTransactions
import com.eazybytes.springsecuritybasic.repository.AccountTransactionsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BalanceController {
    @Autowired
    private val accountTransactionsRepository: AccountTransactionsRepository? = null

    @GetMapping("/myBalance")
    fun getBalanceDetails(@RequestParam id: Int): List<AccountTransactions?>? {
        val accountTransactions = accountTransactionsRepository!!.findByCustomerIdOrderByTransactionDtDesc(id)
        return accountTransactions
    }
}