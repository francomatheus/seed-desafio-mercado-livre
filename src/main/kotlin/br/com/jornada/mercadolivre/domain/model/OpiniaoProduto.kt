package br.com.jornada.mercadolivre.domain.model

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

@Entity
@Table(name = "opiniaoProduto")
data class OpiniaoProduto(

        @field: NotNull
        @field: Min(1)
        @field: Max(5)
        val nota: Int,

        @field: NotBlank
        val titulo: String,

        @field: NotBlank
        val descricao: String,

        @OneToOne
        val usuario: Usuario,

        @OneToOne
        val produto: Produto
) {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    val id: String? = ""

    constructor(): this(
            nota = 1,
            titulo = "",
            descricao = "",
            usuario = Usuario(),
            produto = Produto()
    ){}

}
