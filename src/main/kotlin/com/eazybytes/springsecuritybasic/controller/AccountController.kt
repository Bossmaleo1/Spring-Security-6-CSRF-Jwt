package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Accounts
import com.eazybytes.springsecuritybasic.repository.AccountsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController {
    @Autowired
    private val accountsRepository: AccountsRepository? = null

    @GetMapping("/myAccount")
    fun getAccountDetails(@RequestParam id: Int): Accounts? {
        val accounts = accountsRepository!!.findByCustomerId(id)
        return accounts
    }
}