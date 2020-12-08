package br.com.jornada.mercadolivre.domain.request

import br.com.jornada.mercadolivre.domain.model.Produto
import br.com.jornada.mercadolivre.domain.model.Usuario
import br.com.jornada.mercadolivre.repository.CategoriaRepository
import org.hibernate.validator.constraints.Length
import org.springframework.util.Assert
import java.math.BigDecimal
import javax.validation.Valid
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

class NovoProdutoRequest(
        @field: NotBlank
        val nome: String,

        @field: NotNull
        @field: Positive
        val valor: BigDecimal,

        @field: NotNull
        @field: Positive
        val quantidadeDisponivel: Int,

        @field: NotBlank
        @field: Length(max = 1000)
        val descricao: String,

        @field: NotBlank
        val idCategoria: String,

        @field:Size(min = 3)
        @field:Valid
        val caracteristica: List<CaracteristicaProdutoResquest>
) {

    fun toModel(categoriaRepository: CategoriaRepository, usuario: Usuario): Produto{
        val categoriaProduto = categoriaRepository.findById(idCategoria).get()
        Assert.notNull(categoriaProduto, "Id da categoria de produto não existe: $idCategoria")

        val caracteristicaProduto =  caracteristica.map { it.toModel() }.toList()

        return Produto(
                nome = nome,
                valor = valor,
                quantidadeDisponivel = quantidadeDisponivel,
                descricao = descricao,
                categoria = categoriaProduto,
                caracteristicaProduto = caracteristicaProduto,
                usuarioDono = usuario
        )
    }

    override fun toString(): String {
        return "NovoProdutoRequest(nome='$nome', valor=$valor, quantidadeDisponivel=$quantidadeDisponivel, descricao='$descricao', idCategoria='$idCategoria', caracteristica=$caracteristica)"
    }


}
