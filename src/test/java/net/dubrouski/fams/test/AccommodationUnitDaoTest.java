package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.impl.AccommodationUnitDaoImpl;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Room;
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
 * 
 * @author ondrej.prazak
 *
 */
@RunWith(Arquillian.class)
public class AccommodationUnitDaoTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war")
						 .addPackage("net.dubrouski.fams.model")
						 .addPackage("net.dubrouski.fams.dao")
						 .addPackage("net.dubrouski.fams.dao.impl")
						 .addPackage("net.dubrouski.fams.model.enums")
						 .addClasses(Resources.class, FmsException.class,
								 	 LocalDatePersistenceConverter.class)
						 .addAsResource("META-INF/persistence.xml",
										"META-INF/persistence.xml")
						 .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
//	@Inject
//	AddressDao addresDao;
	
	@Inject
	AccommodationUnitDao unitDao;
	
	@Test
	public void createTest(){
		AccommodationUnit place = new Place();
		place.setIsActive(false);
		place.setName("nice 4+1");
		place.setDepositAmount(BigDecimal.valueOf(4321.50));
		int unitCount = unitDao.listAll().size();
		unitDao.save(place);
		assertEquals(unitCount + 1, unitDao.listAll().size());
	}
}
