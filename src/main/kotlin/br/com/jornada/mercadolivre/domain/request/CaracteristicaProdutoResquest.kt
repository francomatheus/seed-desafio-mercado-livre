package br.com.jornada.mercadolivre.domain.request

import br.com.jornada.mercadolivre.domain.model.CaracteristicaProduto
import javax.validation.constraints.NotBlank

class CaracteristicaProdutoResquest(
        @field:NotBlank
        val nome: String,

        @field:NotBlank
        val valor: String
) {

    fun toModel(): CaracteristicaProduto{
        return CaracteristicaProduto(nome = nome, valor = valor)
    }

    override fun toString(): String {
        return "CaracteristicaProdutoResquest(nome='$nome', valor='$valor')"
    }


}
