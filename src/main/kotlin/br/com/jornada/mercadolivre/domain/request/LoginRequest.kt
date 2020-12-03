package br.com.jornada.mercadolivre.domain.request

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank


class LoginRequest(
        @field: NotBlank
        @field: Email
        val login: String,

        @field: NotBlank
        @field: Length(min = 6)
        val senha: String
) {

}
