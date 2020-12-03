package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.configuration.security.jwt.TokenManager
import br.com.jornada.mercadolivre.domain.request.LoginRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.AuthenticatedPrincipal
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/v1/login")
class LoginResource(
        private val authenticationManager: AuthenticationManager,
        private val tokenManager: TokenManager
) {

    private val logger: Logger = LoggerFactory.getLogger(LoginResource::class.java)

    @Tag(name = "Login")
    @PostMapping
    fun login(@RequestBody @Valid loginRequest: LoginRequest): ResponseEntity<TokenResponseDto> {
        logger.info("Requisição recebida para login de: ${loginRequest.login}")

        val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(loginRequest.login, loginRequest.senha)

        try {
            val authenticate = authenticationManager.authenticate(usernamePasswordAuthenticationToken)
            val tokenGerado = tokenManager.generateToken(authenticate)
            val token = TokenResponseDto(accessToken = tokenGerado)

            logger.info("Login realizado com sucesso, para: ${loginRequest.login}")
            return ResponseEntity.ok(token)
        }catch (exception: Exception){
            exception.printStackTrace()
            return ResponseEntity.notFound().build()
        }
    }
}