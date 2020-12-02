package br.com.jornada.mercadolivre.handler

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ValidacaoHandlerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun erroValidacao(exception: MethodArgumentNotValidException): ResponseEntity<ErroPadraoDto>{
        val todosErros = ArrayList<String>()

        exception.bindingResult.fieldErrors
                .map {
                    todosErros.add("${it.field}: ${it.defaultMessage}")
                }

        val erroPadraoDto = ErroPadraoDto(status = HttpStatus.BAD_REQUEST, erros = todosErros, causa = exception.cause.toString())

        return ResponseEntity.badRequest().body(erroPadraoDto)
    }
}