package net.dubrouski.fams.service.impl;

import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.service.AddressService;

@Named(value = "addressService")
@Stateful
@RolesAllowed("contractAdmin")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class AddressServiceImpl implements AddressService {

	@Inject
	AddressDao addressDao;

	@Inject
	CountryDao countryDao;

	@Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void saveAddress(Address address) {
		addressDao.save(address);
	}

	@Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteAddress(Address address) {
		addressDao.delete(address);
	}

	@Override
        @TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateAddress(Address address) {
		addressDao.update(address);
	}
	
	@Override
	public Address getAddressById(Long id){
		return addressDao.getByID(id);
	}
	
	@Override
	public List<Address> listAddresses(){
		return addressDao.listAll();
	}

	@Override
	public List<Country> getCountriesList() {
		return countryDao.listAll();
	}

}
