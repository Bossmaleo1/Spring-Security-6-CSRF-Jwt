package com.eazybytes.springsecuritybasic.controller

import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
class LoginController {
    @Autowired
    private val customerRepository: CustomerRepository? = null

    @Autowired
    private val passwordEncoder: PasswordEncoder? = null

    @PostMapping("/register")
    fun registerUser(@RequestBody customer: Customer): ResponseEntity<*>? {
        var savedCustomer: Customer? = null
        var response: ResponseEntity<*>? = null
        try {
            val hashPwd = passwordEncoder!!.encode(customer.pwd)
            customer.pwd = hashPwd
            customer.createDt = Date(System.currentTimeMillis()).toString()
            savedCustomer = customerRepository!!.save(customer)
            if (savedCustomer.id > 0) {
                response = ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Given user details are successfully registered")
            }
        } catch (ex: Exception) {
            response = ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An exception occured due to " + ex.message)
        }
        return response
    }

    @RequestMapping("/user")
    fun getUserDetailsAfterLogin(authentication: Authentication): Customer? {
        val customers = customerRepository!!.findByEmail(authentication.name)
        return if (customers.isNotEmpty()) {
            customers[0]
        } else {
            null
        }
    }
}