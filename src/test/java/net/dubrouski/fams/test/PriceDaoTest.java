package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.Price;
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
public class PriceDaoTest {
	@Deployment
	public static Archive<?> createTestArchive(){
		return ShrinkWrap.create(WebArchive.class, "test.war")
						 .addPackage("net.dubrouski.fams.model")
						 .addPackage("net.dubrouski.fams.dao")
						 .addPackage("net.dubrouski.fams.dao.impl")
						 .addPackage("net.dubrouski.fams.test.helper")
						 .addPackage("net.dubrouski.fams.validator")
						 .addPackage("net.dubrouski.fams.annotations")
						 .addPackage("net.dubrouski.fams.model.enums")
						 .addPackage("net.dubrouski.fams.filter")
						 .addClasses(Resources.class, FmsException.class,
						 	 LocalDatePersistenceConverter.class)
						 .addAsResource("META-INF/persistence.xml",
								"META-INF/persistence.xml")
						 .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	PriceDao priceDao;
	
	@Test
	public void createTest(){
		Price p = new Price();
		p.setBasePrice(BigDecimal.valueOf(500));
		p.setCurrency("CZK");
		p.setServicesPrice(BigDecimal.valueOf(50));
		p.setValidFrom(LocalDate.of(2014, 12, 15));
		p.setValidTo(LocalDate.of(2015, 12, 15));
		priceDao.save(p);
		
		assertNotNull(p.getId());
	}
	
	@Test(expected = Exception.class) 
	public void createInvalidDateTest(){
		Price p = new Price();
		p.setBasePrice(BigDecimal.valueOf(500));
		p.setCurrency("CZK");
		p.setServicesPrice(BigDecimal.valueOf(50));
		p.setValidFrom(LocalDate.of(2015, 12, 15));
		p.setValidTo(LocalDate.of(2014, 12, 15));		
		priceDao.save(p);		
	}
}
