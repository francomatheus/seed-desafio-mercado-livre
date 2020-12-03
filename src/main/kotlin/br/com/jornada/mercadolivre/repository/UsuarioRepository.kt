package br.com.jornada.mercadolivre.repository

import br.com.jornada.mercadolivre.domain.model.Usuario
import org.springframework.data.repository.CrudRepository

interface UsuarioRepository: CrudRepository<Usuario, String> {

    fun findByLogin(login: String): Usuario?
}
