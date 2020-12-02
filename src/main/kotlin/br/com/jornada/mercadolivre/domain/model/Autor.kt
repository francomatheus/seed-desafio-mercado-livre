package br.com.jornada.mercadolivre.domain.model

import org.hibernate.annotations.GenericGenerator
import org.hibernate.validator.constraints.Length
import java.time.LocalDateTime
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "autor")
class Autor(
        @field: NotBlank
        @field: Email
        val login: String,

        @field: NotBlank
        @field: Length(min = 6)
        val senha: String
) {
    constructor(): this(
            login = "",
            senha = ""
    )

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String = ""

    @field: NotNull
    val instanteCriacao: LocalDateTime = LocalDateTime.now()

    @field: NotNull
    val hashSenha: String = this.senha
}
