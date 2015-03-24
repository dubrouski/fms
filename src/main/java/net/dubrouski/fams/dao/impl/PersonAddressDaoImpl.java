package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.model.PersonAddress;

/**
 * @author stanislau.dubrouski
 *
 */
@Stateless
@Named(value="personAddressDao")
public class PersonAddressDaoImpl extends BaseDaoImpl<PersonAddress, Long>
		implements PersonAddressDao {

}
