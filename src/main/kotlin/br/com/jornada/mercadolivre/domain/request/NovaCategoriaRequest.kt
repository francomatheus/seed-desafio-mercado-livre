package br.com.jornada.mercadolivre.domain.request

import br.com.jornada.mercadolivre.annotation.ValorUnico
import br.com.jornada.mercadolivre.domain.model.Categoria
import org.springframework.util.Assert
import javax.persistence.EntityManager
import javax.validation.constraints.NotBlank

class NovaCategoriaRequest(
        @field: NotBlank
        @field: ValorUnico(fieldName = "nome", className = "Categoria")
        val nome: String,

        val idCategoriaMae: String?
) {

    fun toModel(manager: EntityManager): Categoria{

        val novaCategoria = Categoria(nome = nome)

        if (idCategoriaMae!== null){
            val categoriaMaeBuscada = manager.find(Categoria::class.java, idCategoriaMae)
            Assert.notNull(categoriaMaeBuscada, "Categoria não encontrada para id: $idCategoriaMae")
            novaCategoria.adicionaCategoriaMae(categoriaMaeBuscada)
        }

        return novaCategoria
    }

}
