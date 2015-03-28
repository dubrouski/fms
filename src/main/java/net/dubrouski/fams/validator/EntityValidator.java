package net.dubrouski.fams.validator;

import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.*;

public class EntityValidator{

	public static <T> void validate(BaseEntity entity) {
		if (entity == null){
			throw new FmsException("Entity is null");  
		}
		if(entity.getId() == null) {
			throw new FmsException("Cannot find id: not an Entity: " + entity.toString());
		}
	}
}
