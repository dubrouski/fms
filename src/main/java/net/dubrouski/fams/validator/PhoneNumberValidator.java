package net.dubrouski.fams.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.dubrouski.fams.annotations.PhoneNumber;

/**
 * @author Stanislau.Dubrouski
 *
 */
public class PhoneNumberValidator implements
		ConstraintValidator<PhoneNumber, String> {

	@Override
	public void initialize(PhoneNumber phoneNumber) {
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		String pattern = "\\+\\d\\d\\d([,\\s])?\\d\\d\\d([,\\s])?\\d\\d\\d([,\\s])?\\d\\d\\d";
		return phoneNumber.matches(pattern);

	}

}
