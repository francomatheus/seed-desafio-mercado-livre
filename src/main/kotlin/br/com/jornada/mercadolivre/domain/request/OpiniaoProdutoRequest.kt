package br.com.jornada.mercadolivre.domain.request

import br.com.jornada.mercadolivre.domain.model.OpiniaoProduto
import br.com.jornada.mercadolivre.domain.model.Produto
import br.com.jornada.mercadolivre.domain.model.Usuario
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import kotlin.math.max

data class OpiniaoProdutoRequest(

        @field: NotNull
        @field: Min(1)
        @field: Max(5)
        val nota: Int,

        @field: NotBlank
        val titulo: String,

        @field: NotBlank
        @field:Length(max = 500)
        val descricao: String

) {

    fun toModel(produto: Produto, usuarioByLogin: Usuario): OpiniaoProduto {

        return OpiniaoProduto(
                nota = nota,
                titulo = titulo,
                descricao = descricao,
                usuario = usuarioByLogin,
                produto = produto
        )
    }

}
