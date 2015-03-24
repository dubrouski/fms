package net.dubrouski.fams.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
/**
 * 
 * @author ondrej.prazak
 *
 */
@Entity
@DiscriminatorValue("place")
public class Place extends AccommodationUnit{

	private static final long serialVersionUID = 1L;

}
