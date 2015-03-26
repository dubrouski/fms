package net.dubrouski.fams.test;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.CountryDao;
import net.dubrouski.fams.dao.impl.BaseDaoImpl;
import net.dubrouski.fams.dao.impl.CountryDaoImpl;
import net.dubrouski.fams.model.BaseEntity;
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

/**
 * @author stanislau.dubrouski
 *
 */
@RunWith(Arquillian.class)
public class CountryDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addClasses(Country.class, CountryDao.class,
						CountryDaoImpl.class, BaseDao.class, BaseDaoImpl.class,
						Resources.class, BaseEntity.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	Logger log;

	@Inject
	CountryDao countryDao;

	@Test
	public void testSave() throws Exception {
		Country c = new Country();
		c.setCode("CZ");
		countryDao.save(c);
		assertNotNull(c.getId());
	}
}
