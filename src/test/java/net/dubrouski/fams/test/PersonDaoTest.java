package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.validation.ValidationException;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.dao.impl.PersonDaoImpl;
import net.dubrouski.fams.dao.impl.BaseDaoImpl;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Country;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.PersonAddress;
import net.dubrouski.fams.model.enums.AddressType;
import net.dubrouski.fams.util.Resources;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.spi.ArquillianProxyException;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author stanislau.dubrouski
 *
 */
@RunWith(Arquillian.class)
public class PersonDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("net.dubrouski.fams.model")
				.addPackage("net.dubrouski.fams.dao")
				.addPackage("net.dubrouski.fams.dao.impl")
				.addPackage("net.dubrouski.fams.model.enums")
				.addClasses(Resources.class,
						LocalDatePersistenceConverter.class, FmsException.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Logger log;

	@Inject
	PersonDao personDao;

	@Before
	public void clearPersons() {
		for (Person p : personDao.listAll()) {
			personDao.delete(p);
		}
	}

	@Test
	public void testBasicSave() throws Exception {
		Person p = getTestPerson();
		personDao.save(p);
		assertNotNull(p.getId());
	}

	@Test
	public void testGetByID() {
		Person p = getTestPerson();
		personDao.save(p);

		Person p2 = personDao.getByID(p.getId());
		assertNotNull(p2);
		assertEquals(p2.toString(), p.toString());
	}

	@Test
	public void testGetByLegalId() {
		Person p = getTestPerson();
		personDao.save(p);

		Person p2 = personDao.getByLegalId(p.getLegalId());
		assertNotNull(p2);
		assertEquals(p2.toString(), p.toString());
	}

	//TODO expect right exception?
	@Test(expected = ArquillianProxyException.class)
	public void testSaveWithNullFirstName() {
		Person p = getTestPerson();
		p.setFirstName(null);
		personDao.save(p);
	}
	
	@Test(expected = ArquillianProxyException.class)
	public void testWithDuplicatedLegalId(){
		Person p = getTestPerson();
		personDao.save(p);
		
		Person p2 = getTestPerson();
//		p2.setLegalId("KI10293894");
		personDao.save(p2);
		
		
	}

	private Person getTestPerson() {
		Person p = new Person();
		p.setFirstName("Standa");
		p.setLastName("Novak");
		p.setBirthDate(LocalDate.now());
		p.setEmail("email@email.com");
		p.setLegalId("KH1789789");
		p.setPhone("+420 777 777 777");
		p.setOtherNames("Bystry Voko");
		return p;
	}

}
