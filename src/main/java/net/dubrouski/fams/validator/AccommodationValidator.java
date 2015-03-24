package net.dubrouski.fams.validator;

import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;

public class AccommodationValidator {
	
	public static void validate(AccommodationUnit unit) {
		if (unit == null){
			throw new FmsException("Cannot add null as AccommodationUnit");  
		}
		if(unit.getId() == null) {
			throw new FmsException("Cannot find id: not an Entity");
		}
	}
}
