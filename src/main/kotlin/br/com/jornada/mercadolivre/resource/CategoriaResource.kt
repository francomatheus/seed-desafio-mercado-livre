package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.domain.request.NovaCategoriaRequest
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
@RequestMapping("/v1/categorias")
class CategoriaResource(
        private final val manager: EntityManager
) {

    private val logger:Logger = LoggerFactory.getLogger(CategoriaResource::class.java)

    @Tag(name = "Categoria")
    @PostMapping
    @Transactional
    fun criarCategoria(@RequestBody @Valid novaCategoriaRequest: NovaCategoriaRequest, uriComponentsBuilder: UriComponentsBuilder): ResponseEntity<String>{
        logger.info("Requisição recebida para criar uma nova categoria: $novaCategoriaRequest")

        val novaCategoria = novaCategoriaRequest.toModel(manager)
        manager.persist(novaCategoria)

        logger.info("Categoria armazenada com sucesso. Id: ${novaCategoria.id}")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/categorias/{id}").buildAndExpand(novaCategoria.id).toUri())
                .build()
    }
}