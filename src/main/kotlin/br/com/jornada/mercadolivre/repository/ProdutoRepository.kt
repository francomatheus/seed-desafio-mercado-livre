package br.com.jornada.mercadolivre.repository

import br.com.jornada.mercadolivre.domain.model.Produto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface ProdutoRepository: CrudRepository<Produto, String> {

}
