package br.com.jornada.mercadolivre.resource

class TokenResponseDto(
        val type: String = "Bearer",

        val accessToken: String
) {

}
