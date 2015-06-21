package net.dubrouski.fams.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.test.helper.PersonTestHelper;
import net.dubrouski.fams.util.Resources;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class PersonAddressDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("net.dubrouski.fams.model")
				.addPackage("net.dubrouski.fams.dao")
				.addPackage("net.dubrouski.fams.dao.impl")
				.addPackage("net.dubrouski.fams.model.enums")
				.addClasses(Resources.class,
						LocalDatePersistenceConverter.class,
						FmsException.class, PersonTestHelper.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	AddressDao addressDao;

	@Inject
	PersonAddressDao personAddressDao;

	@Inject
	PersonDao personDao;

	@Inject
	PersonTestHelper pth;

	@Test
	public void testSave() throws Exception {

		Address a = new Address();
		a.setCity("Brno");
		a.setStreetName("Botanicka");
		a.setStreetNumber("35a");
		a.setFlatNumber("kancl c.5");
		addressDao.save(a);

		PersonAddress pa = new PersonAddress();
		pa.setAddress(a);
		pa.setAddressType(AddressType.Registration);
		personAddressDao.save(pa);

		assertNotNull(pa.getId());

		PersonAddress loadedPa = personAddressDao.getByID(pa.getId());

		assertEquals(pa.getAddress().getId(), loadedPa.getAddress().getId());
		assertEquals(pa.getAddressType(), loadedPa.getAddressType());
	}

	// TODO extract to methods for test data preparation
	@Test
	public void testGetAddressesForPerson() {
		Address a = new Address();
		a.setCity("Brno");
		a.setStreetName("Botanicka");
		a.setStreetNumber("35a");
		a.setFlatNumber("kancl c.5");
		addressDao.save(a);

		PersonAddress pa = new PersonAddress();
		pa.setAddress(a);
		pa.setAddressType(AddressType.Registration);
		personAddressDao.save(pa);

		Address a2 = new Address();
		a2.setCity("Brno");
		a2.setStreetName("Botanicka");
		a2.setStreetNumber("35a");
		a2.setFlatNumber("kancl c.5");
		addressDao.save(a2);

		PersonAddress pa2 = new PersonAddress();
		pa2.setAddress(a);
		pa2.setAddressType(AddressType.Contact);
		personAddressDao.save(pa2);

		Address a3 = new Address();
		a3.setCity("Brno");
		a3.setStreetName("Botanicka");
		a3.setStreetNumber("35a");
		a3.setFlatNumber("kancl c.5");
		addressDao.save(a3);

		PersonAddress pa3 = new PersonAddress();
		pa3.setAddress(a);
		pa3.setAddressType(AddressType.Registration);
		personAddressDao.save(pa3);

		Person person1 = pth.getTestPerson();
		person1.setLegalId("ONE");
		Person person2 = pth.getTestPerson();
		person2.setLegalId("TWO");

		personDao.save(person1);
		personDao.save(person2);

		person1.addAddress(pa);
		person1.addAddress(pa2);
		person2.addAddress(pa3);

		personDao.update(person1);
		personDao.update(person2);

		List<PersonAddress> person1Adr = personAddressDao
				.getAddressesForPerson(person1);
		List<PersonAddress> person2Adr = personAddressDao
				.getAddressesForPerson(person2);

		assertEquals(2, person1Adr.size());
		assertEquals(1, person2Adr.size());

	}

}
