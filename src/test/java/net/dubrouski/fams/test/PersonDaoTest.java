package net.dubrouski.fams.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.test.helper.PersonTestHelper;
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
				.addPackage("net.dubrouski.fams.exception")
				.addClasses(Resources.class,
						LocalDatePersistenceConverter.class,
						PersonTestHelper.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Logger log;

	@Inject
	PersonDao personDao;

	@Inject
	PersonTestHelper pth;

	@Before
	public void clearPersons() {
		for (Person p : personDao.listAll()) {
			personDao.delete(p);
		}
	}

	@Test
	public void testBasicSave() throws Exception {
		Person p = pth.getTestPerson();
		personDao.save(p);
		assertNotNull(p.getId());
	}

	@Test
	public void testListAllPersons() throws Exception {

		List<Person> persons = personDao.listAll();
		assertEquals(0, persons.size());

		Person p = pth.getTestPerson();
		p.setPhone("775775775");
		p.setLegalId("182");
		p.setEmail("email@mail.com");

		Person p2 = pth.getTestPerson();
		p2.setPhone("9238945234");
		p2.setLegalId("sdf234d");
		p2.setEmail("emailemail@mail.com");

		Person p3 = pth.getTestPerson();
		p3.setPhone("1234596789");
		p3.setLegalId("sdhfgkjsdf");
		p3.setEmail("email@somemail.com");

		personDao.save(p);
		personDao.save(p2);
		personDao.save(p3);

		persons = personDao.listAll();

		assertEquals(3, persons.size());
	}

	@Test
	public void testGetByID() {
		Person p = pth.getTestPerson();
		personDao.save(p);

		Person p2 = personDao.getByID(p.getId());
		assertNotNull(p2);
		assertEquals(p2.toString(), p.toString());
	}

	@Test
	public void testGetByNotExistingLegalId() {
		Person p = pth.getTestPerson();
		personDao.save(p);

		Person p2 = personDao.getByLegalId(p.getLegalId());
		assertNotNull(p2);
		assertEquals(p2.toString(), p.toString());
	}

	@Test
	public void testGetByExistingLegalId() {
		Person p = pth.getTestPerson();
		personDao.save(p);

		Person p2 = personDao.getByLegalId("SomeAnotherLID");
		assertNull(p2);
	}

	@Test
	public void testGetByLegalIdOnEmptyDB() {
		Person p = personDao.getByLegalId("something");
		assertNull(p);
	}

	@Test(expected = ArquillianProxyException.class)
	public void testSaveWithNullFirstName() {
		Person p = pth.getTestPerson();
		p.setFirstName(null);
		personDao.save(p);
	}

	// TODO test for different null values;

	@Test
	public void testSimpleUpdate() {
		Person p = pth.getTestPerson();
		personDao.save(p);

		p.setFirstName("Standa (updated)");
		p.setLastName("Novak (updated)");
		p.setBirthDate(LocalDate.of(1981, 11, 2));
		p.setEmail("updated@email.com");
		p.setLegalId("KH1789789 UPD");
		p.setPhone("+420 111 111 111");
		p.setOtherNames("Updated name");

		personDao.update(p);

		Person up = personDao.getByID(p.getId());

		assertEquals(up.getFirstName(), "Standa (updated)");
		assertEquals(up.getLastName(), "Novak (updated)");
		assertEquals(up.getBirthDate(), LocalDate.of(1981, 11, 2));
		assertEquals(up.getEmail(), "updated@email.com");
		assertEquals(up.getLegalId(), "KH1789789 UPD");
		assertEquals(up.getPhone(), "+420 111 111 111");
		assertEquals(up.getOtherNames(), "Updated name");

	}

	@Test
	public void testSimpleDelete() {
		Person p = pth.getTestPerson();
		personDao.save(p);

		assertEquals(1, personDao.listAll().size());

		personDao.delete(p);

		assertEquals(0, personDao.listAll().size());

	}
}
