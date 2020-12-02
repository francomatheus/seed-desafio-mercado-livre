package br.com.jornada.mercadolivre.validator

import br.com.jornada.mercadolivre.annotation.ValorUnico
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

@Component
class ValorUnicoValidator(
        private final val manager: EntityManager
): ConstraintValidator<ValorUnico, String> {

    var fieldName:String = ""
    var className: String = ""

    override fun initialize(constraintAnnotation: ValorUnico) {
        fieldName = constraintAnnotation.fieldName
        className = constraintAnnotation.className
    }

    override fun isValid(value: String, context: ConstraintValidatorContext): Boolean {

        val valorBuscado = manager.createQuery("select v from ${className} v where v.${fieldName} =: pValue")
                .setParameter("pValue", value)
                .resultList

        return !(valorBuscado.size>0)
    }

}
