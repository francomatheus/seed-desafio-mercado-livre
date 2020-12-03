package br.com.jornada.mercadolivre.configuration.security.jwt

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenFilter(
        val tokenManager: TokenManager
): OncePerRequestFilter() {

    private val logger: Logger = LoggerFactory.getLogger(JwtTokenFilter::class.java)

    override fun doFilterInternal(httpServletRequest: HttpServletRequest, httpServletResponse: HttpServletResponse, chain: FilterChain) {

        val tokenFromHeader = getTokenFromHeader(httpServletRequest)
        if (tokenFromHeader != null && tokenManager.isValid(tokenFromHeader)){

                val usuarioFromHeader = tokenManager.getUserFromHeader(tokenFromHeader)

                SecurityContextHolder.getContext().authentication = usuarioFromHeader
        }

        chain.doFilter(httpServletRequest, httpServletResponse)
    }

    private fun getTokenFromHeader(httpServletRequest: HttpServletRequest): String? {
        val token: String? = httpServletRequest.getHeader("Authorization")

        if( (token == null || !token?.startsWith("Bearer ")) ||  token.isEmpty()){
            return null
        }

        return token.substring(7)

    }
}