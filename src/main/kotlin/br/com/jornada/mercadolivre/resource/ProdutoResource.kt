package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.domain.request.NovoProdutoRequest
import br.com.jornada.mercadolivre.repository.CategoriaRepository
import br.com.jornada.mercadolivre.repository.ProdutoRepository
import br.com.jornada.mercadolivre.repository.UsuarioRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/v1/produtos")
class ProdutoResource(
        private val usuarioRepository: UsuarioRepository,
        private val produtoRepository: ProdutoRepository,
        private val categoriaRepository: CategoriaRepository
) {

    private val logger: Logger = LoggerFactory.getLogger(ProdutoResource::class.java)

    @Tag(name = "Produto")
    @PostMapping
    fun criaProduto(
            @RequestBody @Valid novoProdutoRequest: NovoProdutoRequest,
            uriComponentsBuilder: UriComponentsBuilder,
            @AuthenticationPrincipal usuarioLogado: Authentication
    ): ResponseEntity<String>{
        logger.info("Requisição para criar um novo produto recbida: $novoProdutoRequest")

        val dadosUsuario:String = usuarioLogado.principal.toString()

        val usuarioDono = usuarioRepository.findByLogin(dadosUsuario)
        if (usuarioDono == null){
            logger.warn("Usuario dono do produto não encontrado: $dadosUsuario")
            return ResponseEntity.notFound().build()
        }
        val novoProduto = novoProdutoRequest.toModel(categoriaRepository, usuarioDono)

        val produto = produtoRepository.save(novoProduto)

        logger.info("Usuario Logado: $dadosUsuario")
        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/produtos/{id}").buildAndExpand(produto.id).toUri())
                .build()
    }
}