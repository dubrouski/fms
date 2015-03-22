package net.dubrouski.fams.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.PersonAddressDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;
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
				.addClasses(Resources.class, LocalDatePersistenceConverter.class)
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

}
