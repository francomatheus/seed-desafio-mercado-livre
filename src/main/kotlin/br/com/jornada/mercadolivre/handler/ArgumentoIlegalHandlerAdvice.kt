package br.com.jornada.mercadolivre.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ArgumentoIlegalHandlerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun argumentoIlegal(exception: IllegalArgumentException): ResponseEntity<ErroPadraoDto> {

        val erroPadraoDto = ErroPadraoDto(
                status = HttpStatus.UNPROCESSABLE_ENTITY,
                erros = listOf(exception.message.toString()),
                causa = exception.cause.toString()
        )

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erroPadraoDto)
    }
}