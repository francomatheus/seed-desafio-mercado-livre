package br.com.jornada.mercadolivre.repository

import br.com.jornada.mercadolivre.domain.model.OpiniaoProduto
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OpiniaoProdutoRepository: CrudRepository<OpiniaoProduto, String> {

}
