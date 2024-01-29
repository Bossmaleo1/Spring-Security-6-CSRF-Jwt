package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Loans
import com.eazybytes.springsecuritybasic.repository.LoanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LoansController {
    @Autowired
    private val loanRepository: LoanRepository? = null

    @GetMapping("/myLoans")
    @PostAuthorize("hasRole('USER')")
    fun getLoanDetails(@RequestParam id: Int): List<Loans?>? {
        val loans = loanRepository!!.findByCustomerIdOrderByStartDtDesc(id)
        return loans
    }
}