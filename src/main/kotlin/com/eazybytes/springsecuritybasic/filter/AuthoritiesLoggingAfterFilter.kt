package com.eazybytes.springsecuritybasic.filter

import jakarta.servlet.*
import org.springframework.security.core.context.SecurityContextHolder
import java.io.IOException
import java.util.logging.Logger


class AuthoritiesLoggingAfterFilter : Filter {

    private val LOG: Logger = Logger.getLogger(AuthoritiesLoggingAfterFilter::class.java.name)

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (null != authentication) {
            LOG.info(
                "User " + authentication.name + " is successfully authenticated and "
                        + "has the authorities " + authentication.authorities.toString()
            )
        }
        chain.doFilter(request, response)
    }
}