package br.com.jornada.mercadolivre.handler

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class ErroPadraoDto(
        val instante: LocalDateTime = LocalDateTime.now(),
        val status: HttpStatus,
        val erros: List<String>,
        val causa: String
) {

}
