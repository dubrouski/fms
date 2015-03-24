package net.dubrouski.fams.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import net.dubrouski.fams.exception.FmsException;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Entity
@DiscriminatorValue("room")
public class Room extends AccommodationComposite{

	private static final long serialVersionUID = 1L;

	@Override
	protected void unitTypeCheck(AccommodationUnit unit) {
		if(!(unit instanceof Place)){
			throw new FmsException("Cannot add type: "+ unit.getClass() + "into Room");
		}		
	}

}
