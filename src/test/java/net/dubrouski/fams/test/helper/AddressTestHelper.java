package net.dubrouski.fams.test.helper;

import javax.ejb.Stateless;
import javax.inject.Named;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;

@Stateless
@Named
public class AddressTestHelper {
	
	public Address getTestAddress(String city,
			  					String street,
			  					String strNo,
			  					String flatNo){
		Address a = new Address();
		a.setCity(city);
		a.setStreetName(street);
		a.setStreetNumber(strNo);
		a.setFlatNumber(flatNo);
		return a;
	}
	
	public Address getTestAddressWithCountry(String city,
											 String street,
											 String strNo,
											 String flatNo, 
											 Country country){
		Address a = getTestAddress(city, street, strNo, flatNo);
		a.setCountry(country);
		return a;
	}
	
	
	public Address getTestGeoAddress(String city,
								  String street,
								  String strNo,
								  String flatNo,
								  String lat,
								  String lon){
		Address a = new Address();
		a.setCity(city);
		a.setStreetName(street);
		a.setStreetNumber(strNo);
		a.setFlatNumber(flatNo);
		a.setLatitude(lat);
		a.setLongitude(lon);
		return a;
	}
	
	public Address getTestGeoAddressWithCountry(String city,
			  								 String street,
			  								 String strNo,
			  								 String flatNo,
			  								 String lat,
			  								 String lon, 
			  								 Country country){
		Address a = getTestGeoAddress(city, street, strNo, flatNo, lat, lon);
		a.setCountry(country);
		return a;
	}
}
