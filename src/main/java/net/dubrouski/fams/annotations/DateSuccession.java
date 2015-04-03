package net.dubrouski.fams.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import net.dubrouski.fams.validator.DateSuccessionValidator;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {DateSuccessionValidator.class})
public @interface DateSuccession {
	
	String message() default "date succession error";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
