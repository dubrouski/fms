/**
 * 
 */
package net.dubrouski.fams.annotations;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author stanislau.dubrouski
 *
 */
@NotNull
@NotEmpty
public @interface FilledIn {
	 String message() default "Field is mandatory.";
}
