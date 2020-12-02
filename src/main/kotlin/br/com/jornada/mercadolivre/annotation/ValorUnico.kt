package br.com.jornada.mercadolivre.annotation

import br.com.jornada.mercadolivre.domain.model.Autor
import br.com.jornada.mercadolivre.validator.ValorUnicoValidator
import javax.validation.Constraint
import kotlin.reflect.KClass

@Constraint(validatedBy = arrayOf(ValorUnicoValidator::class))
@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
annotation class ValorUnico(
        val message: String = "ja existe no banco de dados",
        val groups: Array<KClass<Any>> = [],
        val payload: Array<KClass<Any>> = [],
        val fieldName: String,
        val className: String
)
