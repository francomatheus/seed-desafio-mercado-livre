package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.domain.request.NovoUsuarioRequest
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.persistence.EntityManager
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/v1/usuarios")
class UsuarioResource(
        private final val manager: EntityManager
) {

    private val logger:Logger = LoggerFactory.getLogger(UsuarioResource::class.java)

    @Tag(name = "Usuario")
    @PostMapping
    @Transactional
    fun criandoAutor(@RequestBody @Valid novoUsuarioRequest: NovoUsuarioRequest, uriComponentsBuilder: UriComponentsBuilder):ResponseEntity<String>{
        logger.info("Requisição recebida para criar um novo autor: $novoUsuarioRequest")

        val novoAutor = novoUsuarioRequest.toModel()

        manager.persist(novoAutor)

        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/autores/{id}").buildAndExpand(novoAutor.id).toUri())
                .build()
    }
}