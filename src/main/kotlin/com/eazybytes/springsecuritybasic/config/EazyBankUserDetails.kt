/*package com.eazybytes.springsecuritybasic.config

import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service


@Service
class EazyBankUserDetails(
    @Autowired
    val customerRepository: CustomerRepository
) : UserDetailsService {

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(EazyBankUserDetails::class.java)
    }

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        var userName: String
        var password: String
        val authorities: MutableList<GrantedAuthority>
        val customer = customerRepository.findByEmail(username)
        LOGGER.info(String.format("ResponseTest -> %s", customer[0].email))
        LOGGER.info(String.format("ResponseRole -> %s", customer[0].role))
        if (customer.isEmpty()) {
            throw UsernameNotFoundException("User details not found for the user : $username")
        } else {
            userName = customer[0].email.toString()
            password = customer[0].pwd.toString()
            authorities = ArrayList()
            authorities.add(SimpleGrantedAuthority(customer[0].role))
        }
        return User(userName, password, authorities)
    }
}*/