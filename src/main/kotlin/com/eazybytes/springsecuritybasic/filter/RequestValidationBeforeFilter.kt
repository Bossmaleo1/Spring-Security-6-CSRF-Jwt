package com.eazybytes.springsecuritybasic.filter

import jakarta.servlet.*
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.util.StringUtils
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets
import java.util.*


class RequestValidationBeforeFilter : Filter {
    private val credentialsCharset: Charset = StandardCharsets.UTF_8

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        var header = req.getHeader(HttpHeaders.AUTHORIZATION)
        if (header != null) {
            header = header.trim { it <= ' ' }
            if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
                val base64Token = header.substring(6).toByteArray(StandardCharsets.UTF_8)
                val decoded: ByteArray
                try {
                    decoded = Base64.getDecoder().decode(base64Token)
                    val token = String(decoded, credentialsCharset)
                    val delim = token.indexOf(":")
                    if (delim == -1) {
                        throw BadCredentialsException("Invalid basic authentication token")
                    }
                    val email = token.substring(0, delim)
                    if (email.lowercase(Locale.getDefault()).contains("test")) {
                        res.status = HttpServletResponse.SC_BAD_REQUEST
                        return
                    }
                } catch (e: IllegalArgumentException) {
                    throw BadCredentialsException("Failed to decode basic authentication token")
                }
            }
        }
        chain.doFilter(request, response)
    }

    companion object {
        const val AUTHENTICATION_SCHEME_BASIC: String = "Basic"
    }
}