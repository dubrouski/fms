package net.dubrouski.fams.test;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.inject.Inject;

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
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
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
						LocalDatePersistenceConverter.class,
						FmsException.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Logger log;

	@Inject
	PersonDao personDao;

	@Test
	public void testSave() throws Exception {

		Person p = new Person();
		p.setFirstName("Standa");
		p.setLastName("Novak");
		p.setBirthDate(LocalDate.now());
		p.setEmail("email@email.com");
		p.setLegalId("KH1789789");
		p.setPhone("+420 777 777 777");
		p.setOtherNames("Bystry Voko");

		personDao.save(p);

		assertNotNull(p.getId());

	}

}
