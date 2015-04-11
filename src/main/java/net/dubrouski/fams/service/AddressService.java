package net.dubrouski.fams.service;

import java.util.List;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;

public interface AddressService {
	public void saveAddress(Address address);

	public void deleteAddress(Address address);

	public void updateAddress(Address address);
	
	public List<Country> getCountriesList();
}
