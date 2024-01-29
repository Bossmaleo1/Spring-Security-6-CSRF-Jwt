package com.eazybytes.springsecuritybasic.filter

//val key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.toByteArray(StandardCharsets.UTF_8))
import com.eazybytes.springsecuritybasic.constants.SecurityConstants
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*


class JWTTokenGeneratorFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authentication = SecurityContextHolder.getContext().authentication
        if (null != authentication) {
            val key = Keys.hmacShaKeyFor(SecurityConstants.JWT_KEY.toByteArray(StandardCharsets.UTF_8))
            val jwt = Jwts.builder().setIssuer("Eazy Bank").setSubject("JWT Token")
                .claim("username", authentication.name)
                .claim("authorities", populateAuthorities(authentication.authorities))
                .setIssuedAt(Date())
                .setExpiration(Date(Date().time + 30000000))
                .signWith(key).compact()
            response.setHeader(SecurityConstants.JWT_HEADER, jwt)
        }

        filterChain.doFilter(request, response)
    }

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        return request.servletPath != "/user"
    }

    private fun populateAuthorities(collection: Collection<GrantedAuthority>): String {
        val authoritiesSet: MutableSet<String> = HashSet()
        for (authority in collection) {
            authoritiesSet.add(authority.authority)
        }
        return java.lang.String.join(",", authoritiesSet)
    }
}