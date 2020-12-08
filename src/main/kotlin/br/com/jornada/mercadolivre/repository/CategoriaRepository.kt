package br.com.jornada.mercadolivre.repository

import br.com.jornada.mercadolivre.domain.model.Categoria
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CategoriaRepository: CrudRepository<Categoria, String> {

}
