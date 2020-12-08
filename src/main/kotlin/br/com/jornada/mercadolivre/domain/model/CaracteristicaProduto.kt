package br.com.jornada.mercadolivre.domain.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "caracteristicaProduto")
class CaracteristicaProduto(

        @Id
        var id: String? = UUID.randomUUID().toString(),

        @field:NotBlank
        val nome: String,

        @field:NotBlank
        val valor: String
) {


    constructor(): this(
            nome = "",
            valor = ""
    ){

    }
}
