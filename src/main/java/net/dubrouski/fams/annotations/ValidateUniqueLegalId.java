package net.dubrouski.fams.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import net.dubrouski.fams.validator.LegalIdValidator;

/**
 * @author stanislau.dubrouski
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { LegalIdValidator.class })
public @interface ValidateUniqueLegalId {
	String message() default "{errorcode}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}