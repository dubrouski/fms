package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.dao.impl.AddressDaoImpl;
import net.dubrouski.fams.dao.impl.BaseDaoImpl;
import net.dubrouski.fams.dao.impl.CountryDaoImpl;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.BaseEntity;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.service.CurrencyService;
import net.dubrouski.fams.service.impl.CurrencyServiceImpl;
import net.dubrouski.fams.util.Resources;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class AddressDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("net.dubrouski.fams.model")
				.addPackage("net.dubrouski.fams.dao")
				.addPackage("net.dubrouski.fams.dao.impl")
				.addPackage("net.dubrouski.fams.model.enums")
				.addPackage("net.dubrouski.fams.exception")
				.addPackage("net.dubrouski.fams.converter")
				.addPackage("net.dubrouski.fams.service")
				.addPackage("net.dubrouski.fams.service.impl")
				.addClasses(Resources.class, CurrencyService.class,
						CurrencyServiceImpl.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Logger log;

	@Inject
	AddressDao addressDao;

	@Inject
	CountryDao countryDao;

	@Before
	public void removeTestAddresses() {
		for (Address a : addressDao.listAll()) {
			addressDao.delete(a);
		}

		for (Country c : countryDao.listAll()) {
			countryDao.delete(c);
		}

		insertTestCountries();
	}

	private void insertTestCountries() {
		Country cz = new Country();
		cz.setCode("CZ");
		countryDao.save(cz);

		Country sk = new Country();
		sk.setCode("SK");
		countryDao.save(sk);
	}

	@Test
	public void testSave() throws Exception {

		Address a = new Address();
		a.setCountry(countryDao.getCountryByCode("SK"));
		a.setCity("Brno");
		a.setStreetName("Botanicka");
		a.setStreetNumber("35a");
		a.setFlatNumber("kancl c.5");
		a.setLatitude(Double.valueOf("49.4145469"));
		a.setLongitude(Double.valueOf("17.8088378"));

		addressDao.save(a);

		assertNotNull(a.getId());

		Address b = addressDao.getByID(a.getId());
		assertEquals(a.getCountry().getCode(), b.getCountry().getCode());
	}

	@Test
	public void testList() {
		Address adr = new Address();
		adr.setCountry(countryDao.getCountryByCode("CZ"));
		adr.setCity("Brno");
		adr.setStreetName("Botanicka");
		adr.setStreetNumber("35a");
		adr.setFlatNumber("kancl c.5");
		adr.setLatitude(Double.valueOf("49.4145469"));
		adr.setLongitude(Double.valueOf("17.8088378"));

		Address adr2 = new Address();
		adr2.setCountry(countryDao.getCountryByCode("SK"));
		adr2.setCity("Praha");
		adr2.setStreetName("Tridni");
		adr2.setStreetNumber("35987");
		adr2.setFlatNumber("pod stropem");
		adr2.setLatitude(Double.valueOf("49.4145469"));
		adr2.setLongitude(Double.valueOf("17.8088378"));

		addressDao.save(adr);
		addressDao.save(adr2);

		List<Address> retreivedAddresses = addressDao.listAll();

		assertEquals(2, retreivedAddresses.size());

		assertAddressDataWithoutIdEqual(adr, retreivedAddresses.get(0));
		assertAddressDataWithoutIdEqual(adr2, retreivedAddresses.get(1));
	}

	@Test
	public void testDelete() {
		assertEquals(0, addressDao.listAll().size());

		Address address = getAddress();
		addressDao.save(address);
		Address retreivedAddress = addressDao.getByID(address.getId());

		assertNotNull(retreivedAddress);

		addressDao.delete(retreivedAddress);

		assertEquals(0, addressDao.listAll().size());
		Address noAddressRetreived = addressDao.getByID(address.getId());
		assertNull(noAddressRetreived);
	}

	@Test
	public void testUpdate() {
		Address address = getAddress();

		addressDao.save(address);

		address.setCountry(countryDao.getCountryByCode("SK"));
		address.setCity("Praha");
		address.setStreetName("Tridni");
		address.setStreetNumber("as3d2f");
		address.setFlatNumber("ksjhfgskdfgsdf");

		addressDao.update(address);

		Address retreivedAddress = addressDao.getByID(address.getId());

		assertEquals(address.getId(), retreivedAddress.getId());
		assertAddressDataWithoutIdEqual(address, retreivedAddress);
	}

	private Address getAddress() {
		Address adr = new Address();
		adr.setCountry(countryDao.getCountryByCode("CZ"));
		adr.setCity("Brno");
		adr.setStreetName("Botanicka");
		adr.setStreetNumber("35a");
		adr.setFlatNumber("kancl c.5");

		return adr;
	}

	private static void assertAddressDataWithoutIdEqual(Address a, Address b) {
		assertEquals(a.getCountry().getCode(), b.getCountry().getCode());
		assertEquals(a.getCity(), b.getCity());
		assertEquals(a.getStreetName(), b.getStreetName());
		assertEquals(a.getStreetNumber(), b.getStreetNumber());
		assertEquals(a.getFlatNumber(), b.getFlatNumber());
		assertEquals(a.getLatitude(), b.getLatitude());
		assertEquals(a.getLongitude(), b.getLongitude());
	}
}
