package br.com.jornada.mercadolivre.domain.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.Length
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "usuario")
class Usuario(login: String,
              senha: String
): UserDetails {

    constructor(): this(
            "", ""
    )

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = ""

    @field: NotBlank
    @field: Email
    val login = login

    @field: NotBlank
    @field: Length(min = 6)
    val senha: String = BCryptPasswordEncoder().encode(senha)

    @field: NotNull
    val instanteCriacao: LocalDateTime = LocalDateTime.now()

    @field: NotNull
    val hashSenha: String = BCrypt.hashpw(this.senha, BCrypt.gensalt())


    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return null
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.login
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
