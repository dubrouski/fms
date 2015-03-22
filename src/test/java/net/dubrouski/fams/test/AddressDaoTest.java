package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.dao.impl.AddressDaoImpl;
import net.dubrouski.fams.dao.impl.BaseDaoImpl;
import net.dubrouski.fams.dao.impl.CountryDaoImpl;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
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
public class AddressDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Country.class, Address.class, AddressDao.class,
						AddressDaoImpl.class, CountryDao.class,
						CountryDaoImpl.class, BaseDao.class, BaseDaoImpl.class,
						Resources.class)
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

	@Test
	public void testSave() throws Exception {
		
		Country c = new Country();
		c.setCode("CZ");
		countryDao.save(c);
		
		
		Address a = new Address();
		a.setCountry(c);
		a.setCity("Brno");
		a.setStreetName("Botanicka");
		a.setStreetNumber("35a");
		a.setFlatNumber("kancl c.5");
		addressDao.save(a);

		assertNotNull(a.getId());
		
		Address b = addressDao.getByID(a.getId());
		assertEquals(a.getCountry().getCode(), b.getCountry().getCode());
	}
}
