package com.eazybytes.springsecuritybasic.filter

import jakarta.servlet.*
import java.io.IOException
import java.util.logging.Logger


class AuthoritiesLoggingAtFilter : Filter {
    private val LOG: Logger = Logger.getLogger(AuthoritiesLoggingAtFilter::class.java.name)

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        LOG.info("Authentication Validation is in progress")
        chain.doFilter(request, response)
    }
}