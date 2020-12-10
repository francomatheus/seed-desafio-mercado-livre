package br.com.jornada.mercadolivre.resource

import br.com.jornada.mercadolivre.domain.request.OpiniaoProdutoRequest
import br.com.jornada.mercadolivre.repository.OpiniaoProdutoRepository
import br.com.jornada.mercadolivre.repository.ProdutoRepository
import br.com.jornada.mercadolivre.repository.UsuarioRepository
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/v1/produtos")
class OpiniaoProdutoResource(
        private val usuarioRepository: UsuarioRepository,
        private val produtoRepository: ProdutoRepository,
        private val opiniaoProdutoRepository: OpiniaoProdutoRepository
) {

    @Tag(name = "Produtos")
    @PostMapping("/{idProduto}/opinioes")
    fun criaOpiniao(
            @PathVariable("idProduto") idProduto: String,
            @RequestBody @Valid opiniaoProdutoRequest: OpiniaoProdutoRequest,
            uriComponentsBuilder: UriComponentsBuilder,
            @AuthenticationPrincipal usuarioLogado: Authentication
    ): ResponseEntity<String>{
        val produtoById = produtoRepository.findById(idProduto)
        if (produtoById.isEmpty){
            return ResponseEntity.notFound().build()
        }

        val emailUsuarioLogado = usuarioLogado.principal.toString()
        val usuarioByLogin = usuarioRepository.findByLogin(emailUsuarioLogado);
        if (usuarioByLogin == null){
            return ResponseEntity.badRequest().build()
        }

        val opiniaoProduto = opiniaoProdutoRequest.toModel(produtoById.get(), usuarioByLogin)


        val opiniaoProdutoSalvo = opiniaoProdutoRepository.save(opiniaoProduto)


        return ResponseEntity
                .created(uriComponentsBuilder.path("/v1/produtos/{idProduto}/opinioes/{id}").buildAndExpand(idProduto,opiniaoProdutoSalvo.id).toUri())
                .build()
    }

}