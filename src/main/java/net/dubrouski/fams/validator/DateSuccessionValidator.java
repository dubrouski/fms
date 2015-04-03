package net.dubrouski.fams.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.dubrouski.fams.annotations.DateSuccession;
import net.dubrouski.fams.model.Price;

public class DateSuccessionValidator implements ConstraintValidator<DateSuccession, Price > {

	@Override
	public void initialize(DateSuccession arg0) {
		// TODO Auto-generated method stub
		
	}	

	@Override
	public boolean isValid(Price price, ConstraintValidatorContext arg1) {
		return price.validateDates();
	}

}
