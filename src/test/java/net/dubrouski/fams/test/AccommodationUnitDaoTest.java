package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Place;

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
//@RunWith(Arquillian.class)
//public class AccommodationUnitDaoTest {
//	@Deployment
//	public static Archive<?> createTestArchive() {
//		return ShrinkWrap.create(WebArchive.class, "test.war")
//						 .addPackage("net.dubrouski.fams.model")
//						 .addPackage("net.dubrouski.fams.dao")
//						 .addPackage("net.dubrouski.fams.dao.impl")
//						 .addAsResource("META-INF/persistence.xml",
//										"META-INF/persistence.xml")
//						 .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
//	}
//	
//	@Inject
//	AddressDao addresDao;
//	
//	@Inject
//	AccommodationUnitDao unitDao;
//	
//	@Test
//	public void deleteTest(){
//		AccommodationUnit place = new Place();
//		place.setIsActive(false);
//		place.setName("nice 4+1");
//		place.setDepositAmount(BigDecimal.valueOf(4321.50));
//		int unitCount = unitDao.listAll().size();
//		unitDao.save(place);
//		assertEquals(unitCount + 1, unitDao.listAll().size());
//	}
//}
