package br.com.jornada.mercadolivre.domain.request

import br.com.jornada.mercadolivre.annotation.ValorUnico
import br.com.jornada.mercadolivre.domain.model.Autor
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank

class NovoAutorRequest(

        @field: NotBlank
        @field: Email
        @field: ValorUnico(fieldName = "login", className = "Autor")
        val login: String,

        @field: NotBlank
        @field: Length(min = 6)
        val senha: String
) {

    fun toModel(): Autor {
        return Autor(login = this.login, senha = senha)
    }

    override fun toString(): String {
        return "NovoAutorReqeust(login='$login', senha='$senha')"
    }


}
