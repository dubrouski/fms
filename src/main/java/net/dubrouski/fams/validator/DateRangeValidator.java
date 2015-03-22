package net.dubrouski.fams.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.dubrouski.fams.annotations.ValidateDateRanges;

/**
 * @author stanislau.dubrouski
 *
 */
public class DateRangeValidator implements
		ConstraintValidator<ValidateDateRanges, LocalDate> {

	@Override
	public void initialize(ValidateDateRanges arg0) {
		// 
	}

	@Override
	public boolean isValid(LocalDate date,
			ConstraintValidatorContext constraintContext) {
		if (date.isAfter(LocalDate.now())
				|| date.isBefore(LocalDate.of(1900, 1, 1))) {
			return false;
		} else {
			return true;
		}
	}
}
