package br.com.jornada.mercadolivre.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.NotBlank

@Entity
@Table(name = "categoria")
class Categoria(
        @field: NotBlank
        val nome: String,

        @OneToOne
        var categoriaMae: Categoria? = null
) {
    constructor(): this(
            nome = "",
            categoriaMae = null
    )

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = ""

    fun adicionaCategoriaMae(categoriaMaeBuscada: Categoria) {
        this.categoriaMae = categoriaMaeBuscada
    }


}
