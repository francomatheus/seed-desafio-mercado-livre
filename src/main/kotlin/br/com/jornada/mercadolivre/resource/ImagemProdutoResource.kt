package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.domain.model.Produto
import br.com.jornada.mercadolivre.service.ArmazenaImagem
import io.jsonwebtoken.lang.Assert
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import javax.persistence.EntityManager
import javax.transaction.Transactional

@RestController
@RequestMapping("/v1/produtos")
class ImagemProdutoResource(
        private val manager: EntityManager,
        private final val armazenaImagem: ArmazenaImagem
) {

    private val logger: Logger = LoggerFactory.getLogger(ImagemProdutoResource::class.java)

    @PostMapping("/{id}")
    @Transactional
    fun adicionaImagem(
            @PathVariable(name = "id") idProduto: String,
            imagens: List<MultipartFile>,
            @AuthenticationPrincipal usuarioLogado: Authentication
    ): ResponseEntity<String>{

        val produtoBuscado = manager.find(Produto::class.java, idProduto)
        Assert.notNull(produtoBuscado, "Produto não encontrado para id: $idProduto")
        if (produtoBuscado.usuarioDono.login != usuarioLogado.principal.toString()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Produto não pertence ao usuario")
        }

        val pathImagens = armazenaImagem.armazenaImagem(imagens)

        produtoBuscado.adicionaImagem(pathImagens)

        manager.merge(produtoBuscado)

        return ResponseEntity.status(HttpStatus.OK).build()
    }
}