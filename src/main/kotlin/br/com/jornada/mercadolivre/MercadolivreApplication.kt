package br.com.jornada.mercadolivre

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.security.Security

@SpringBootApplication
class MercadolivreApplication

fun main(args: Array<String>) {
	runApplication<MercadolivreApplication>(*args)
}
