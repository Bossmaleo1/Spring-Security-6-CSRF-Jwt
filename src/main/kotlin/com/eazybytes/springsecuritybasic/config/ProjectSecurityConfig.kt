package com.eazybytes.springsecuritybasic.config

/*import com.eazybytes.springsecuritybasic.filter.*
import jakarta.servlet.http.HttpServletRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.web.cors.CorsConfiguration
import java.util.Collections
import org.springframework.web.cors.CorsConfigurationSource;
import java.util.Arrays


@Configuration
@EnableWebSecurity
class ProjectSecurityConfig {

    @Bean
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {

        val requestHandler = CsrfTokenRequestAttributeHandler()
        requestHandler.setCsrfRequestAttributeName("_csrf")

        http
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .cors { corsCustomizer ->
                corsCustomizer.configurationSource {
                    val config = CorsConfiguration()
                    config.allowedOrigins = Collections.singletonList("http://localhost:4200")
                    config.allowedMethods = Collections.singletonList("*")
                    config.allowCredentials = true
                    config.allowedHeaders = Collections.singletonList("*")
                    config.exposedHeaders = listOf("Authorization")
                    config.maxAge = 3600L
                    return@configurationSource config
                }
            }.csrf { csrf ->
                csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            }
            .addFilterAfter(CsrfCookieFilter(), BasicAuthenticationFilter::class.java)
            .addFilterBefore(RequestValidationBeforeFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAfter(AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAt(AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter::class.java)
            .addFilterAfter(JWTTokenGeneratorFilter(), BasicAuthenticationFilter::class.java)
            .addFilterBefore(JWTTokenValidatorFilter(), BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests { requests ->
                requests
                    .requestMatchers("/myAccount").hasRole("USER")
                    .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                    .requestMatchers("/myLoans").hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")
                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/notices", "/contact", "/register").permitAll()
            }
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

}*/

import com.eazybytes.springsecuritybasic.filter.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler
import org.springframework.web.cors.CorsConfiguration


@Configuration
class ProjectSecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun defaultSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val requestHandler = CsrfTokenRequestAttributeHandler()
        requestHandler.setCsrfRequestAttributeName("_csrf")
        http.sessionManagement { session: SessionManagementConfigurer<HttpSecurity?> ->
            session.sessionCreationPolicy(
                SessionCreationPolicy.STATELESS
            )
        }
            .cors { corsCustomizer: CorsConfigurer<HttpSecurity?> ->
                corsCustomizer.configurationSource {
                    val config = CorsConfiguration()
                    config.allowedOrigins = listOf("http://localhost:4200")
                    config.allowedMethods = listOf("*")
                    config.allowCredentials = true
                    config.allowedHeaders = listOf("*")
                    config.exposedHeaders = mutableListOf("Authorization")
                    config.maxAge = 3600L
                    return@configurationSource config
                }
            }.csrf { csrf: CsrfConfigurer<HttpSecurity?> ->
                csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/contact", "/register")
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            }
            .addFilterAfter(CsrfCookieFilter(), BasicAuthenticationFilter::class.java)
            //.addFilterBefore(RequestValidationBeforeFilter(), BasicAuthenticationFilter::class.java)
            //.addFilterAt(AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter::class.java)
            //.addFilterAfter(AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter::class.java)
            //.addFilterAfter(JWTTokenGeneratorFilter(), BasicAuthenticationFilter::class.java)
            //.addFilterBefore(JWTTokenValidatorFilter(), BasicAuthenticationFilter::class.java)
            .authorizeHttpRequests{ requests ->
                requests
                    .requestMatchers("/myAccount").hasRole("USER")
                    .requestMatchers("/myBalance").hasAnyRole("USER", "ADMIN")
                    .requestMatchers("/myLoans").authenticated()  //.hasRole("USER")
                    .requestMatchers("/myCards").hasRole("USER")
                    .requestMatchers("/user").authenticated()
                    .requestMatchers("/notices", "/contact", "/register").permitAll()
            }
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}