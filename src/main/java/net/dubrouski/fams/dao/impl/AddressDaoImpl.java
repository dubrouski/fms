package net.dubrouski.fams.dao.impl;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.model.Address;
@Stateless
@Named(value = "addressDao")
public class AddressDaoImpl extends BaseDaoImpl<Address, Long> implements
		AddressDao {

}
