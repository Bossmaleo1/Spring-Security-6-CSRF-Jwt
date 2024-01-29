package com.eazybytes.springsecuritybasic.config

import com.eazybytes.springsecuritybasic.model.Authority
import com.eazybytes.springsecuritybasic.model.Customer
import com.eazybytes.springsecuritybasic.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component


@Component
class EazyBankUsernamePwdAuthenticationProvider(
    @Autowired
    val customerRepository: CustomerRepository,
    @Autowired
    val passwordEncoder: PasswordEncoder
) : AuthenticationProvider{

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication?): Authentication {
        val userName: String = authentication!!.name
        val pwd: String = authentication.credentials.toString()
        val customer: List<Customer> = customerRepository.findByEmail(userName)
        if (customer.isNotEmpty()) {
            if (passwordEncoder.matches(pwd, customer[0].pwd)) {
                val authorities: MutableList<GrantedAuthority> = mutableListOf()
                authorities.add(SimpleGrantedAuthority(customer[0].role))
                return UsernamePasswordAuthenticationToken(userName,pwd,
                    customer[0].authorities?.let { getGrantedAuthorities(it) })
            } else {
                throw BadCredentialsException("Invalid password!")
            }
        } else {
            throw BadCredentialsException("No user registered with this details!")
        }
    }

    private fun getGrantedAuthorities(authorities: Set<Authority>): List<GrantedAuthority> {
        val grantedAuthorities: MutableList<GrantedAuthority> = ArrayList()
        for (authority in authorities) {
            grantedAuthorities.add(SimpleGrantedAuthority(authority.name))
        }
        return grantedAuthorities
    }

    override fun supports(authentication: Class<*>?): Boolean {
        return (UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication!!))
    }
}