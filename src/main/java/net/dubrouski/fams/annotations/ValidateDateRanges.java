package net.dubrouski.fams.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import net.dubrouski.fams.validator.DateRangeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;


/**
 * @author stanislau.dubrouski
 *
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { DateRangeValidator.class })
public @interface ValidateDateRanges {
	String message() default "{errorcode}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
