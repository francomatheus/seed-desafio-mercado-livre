package br.com.jornada.mercadolivre.domain.model

import org.hibernate.validator.constraints.Length
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import kotlin.collections.ArrayList

@Entity
@Table(name = "produto")
class Produto(
        @field:NotBlank
        val nome: String,

        @field:NotNull
        @field:Positive
        val valor: BigDecimal,

        @field:NotNull
        @field:Positive
        val quantidadeDisponivel: Int,

        @field:NotBlank
        @field:Length(max = 1000)
        val descricao: String,

        @field:NotNull
        val instanteCadastro: LocalDateTime = LocalDateTime.now(),

        @field:NotNull
        @field:Valid
        @OneToOne
        val categoria: Categoria,

        @OneToMany(cascade = arrayOf(CascadeType.ALL))
        val caracteristicaProduto: List<CaracteristicaProduto>,

        @ManyToOne
        val usuarioDono: Usuario
) {
    constructor():this(
            nome = "",
            valor = BigDecimal.ZERO,
            quantidadeDisponivel = 0,
            descricao = "",
            instanteCadastro = LocalDateTime.now(),
            categoria = Categoria(),
            caracteristicaProduto = ArrayList<CaracteristicaProduto>(),
            usuarioDono = Usuario()
    ){ }

    @Id
    var id: String? = UUID.randomUUID().toString()
}
