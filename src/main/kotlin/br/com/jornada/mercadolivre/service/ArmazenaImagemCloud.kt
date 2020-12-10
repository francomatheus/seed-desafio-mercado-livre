package br.com.jornada.mercadolivre.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.web.multipart.MultipartFile

@Profile("prod")
class ArmazenaImagemCloud: ArmazenaImagem {

    private val logger: Logger = LoggerFactory.getLogger(ArmazenaImagemCloud::class.java)

    override fun armazenaImagem(imagens: List<MultipartFile>): ArrayList<String> {
        logger.info("armazena Imagem na cloud e salva path da imagem armazenada no banco de dados")
        return arrayListOf()
    }
}