package net.dubrouski.fams.validator;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import net.dubrouski.fams.annotations.ValidateUniqueLegalId;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;

/**
 * @author stanislau.dubrouski
 *
 */
public class LegalIdValidator implements
		ConstraintValidator<ValidateUniqueLegalId, String> {

	@Inject
	private PersonDao personDao;

	@Override
	public void initialize(ValidateUniqueLegalId arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(String legalId,
			ConstraintValidatorContext constraintContext) {
		Person existing = personDao.getByLegalId(legalId);
		if (existing == null) {
			return true;
		} else {
			return false;
		}
	}
}
