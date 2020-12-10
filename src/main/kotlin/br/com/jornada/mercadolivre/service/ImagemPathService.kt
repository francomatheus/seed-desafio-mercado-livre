package br.com.jornada.mercadolivre.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Profile("dev")
@Service
class ImagemPathService: ArmazenaImagem {

    private val logger: Logger = LoggerFactory.getLogger(ImagemPathService::class.java)

    override fun armazenaImagem(imagens: List<MultipartFile>): ArrayList<String> {
        logger.info("Iniciando armazenamento de imagens e geração do path da imagem")

        val listaImagemPath = arrayListOf<String>()
        imagens.forEach {
            val pathImagem = generatePathImagem(it)
            listaImagemPath.add(pathImagem)
        }

        logger.info("Path de imagens gerado: $listaImagemPath")
        return listaImagemPath
    }

    fun generatePathImagem(imagem: MultipartFile): String{
        logger.info("Gerar path para imagem: ${imagem.originalFilename}")

        val hostPath = "http://www.imagemarmazenada.com"
        val stringBuilder: StringBuilder = StringBuilder()
        val nomeImagem = imagem.originalFilename?.replace(" ", "")

        return stringBuilder.append(hostPath)
                .append("/")
                .append(Math.random() * 10000)
                .append("-")
                .append(nomeImagem).toString()
    }
}