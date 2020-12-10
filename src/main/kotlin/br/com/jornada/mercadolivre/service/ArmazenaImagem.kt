package br.com.jornada.mercadolivre.service

import org.springframework.web.multipart.MultipartFile

interface ArmazenaImagem {

    fun armazenaImagem(imagens: List<MultipartFile>): ArrayList<String>
}
