package net.dubrouski.fams.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.service.AddressService;

@Named(value = "addressService")
public class AddressServiceImpl implements AddressService {

	@Inject
	AddressDao addressDao;

	@Inject
	CountryDao countryDao;

	@Override
	public void saveAddress(Address address) {
		addressDao.save(address);
	}

	@Override
	public void deleteAddress(Address address) {
		addressDao.delete(address);
	}

	@Override
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
