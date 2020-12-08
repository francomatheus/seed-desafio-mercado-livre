package br.com.jornada.mercadolivre.configuration.security.jwt

import br.com.jornada.mercadolivre.repository.UsuarioRepository
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import org.springframework.web.server.ResponseStatusException
import java.util.*

@Service
class TokenManager(
        @Autowired private val usuarioRepository: UsuarioRepository
) {
    private val logger: Logger = LoggerFactory.getLogger(TokenManager::class.java)

    @Value("\${jwt.tempo-expiracao}")
    val tempoDuracaoToken: String = ""

    @Value("\${jwt.secret-key}")
    val secretTokenKey: String = ""

    fun generateToken(authenticate: Authentication): String {
        logger.info("Começando a gerar token para usuario")

        val usuario = authenticate.principal as User

        val tempoTokenGerado = Date()
        val expiracaoToken = Date(tempoTokenGerado.time + tempoDuracaoToken.toLong())

        return Jwts.builder()
                .setIssuer("Api do MercadoLivre")
                .setSubject(usuario.username)
                .setIssuedAt(tempoTokenGerado)
                .setExpiration(expiracaoToken)
                .signWith(SignatureAlgorithm.HS256, secretTokenKey).compact()
    }

    fun isValid(token: String): Boolean{
        logger.info("Verificando se token é valido")
        try {
            val tokenParsed = Jwts.parser().setSigningKey(secretTokenKey).parseClaimsJws(token)
            getUserFromHeader(token)

            logger.info("Token é valido: $token, ")
            return true
        }catch (exception: Exception){
            logger.info("Token invalido: $token")
            logger.warn("Token invalido: ${exception.message}")
            return false
        }
    }

    fun getUserFromHeader(token: String): UsernamePasswordAuthenticationToken {
        logger.info("Coletando usuario a partir do token")
        try {
            val tokenParser = Jwts.parser().setSigningKey(secretTokenKey).parseClaimsJws(token)
            val loginUsuario = tokenParser.body.subject

            val usuarioFromToken = usuarioRepository.findByLogin(loginUsuario)
            Assert.notNull(usuarioFromToken,"Usuario não encontrado no banco de dados.")

            logger.info("Usuario dono do token: $loginUsuario")

            return UsernamePasswordAuthenticationToken(usuarioFromToken?.login,null, arrayListOf())
        }catch (exception: Exception){
            logger.warn("Erro ao tentar pegar usuario do token: ${exception.message}")
            return throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Token invalido.")
        }
    }

}