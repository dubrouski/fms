package net.dubrouski.fams.test;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.BaseDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.dao.impl.AccommodationUnitDaoImpl;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.Room;
import net.dubrouski.fams.test.helper.AddressTestHelper;
import net.dubrouski.fams.test.helper.PriceTestHelper;
import net.dubrouski.fams.util.Resources;
import net.dubrouski.fams.validator.EntityValidator;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
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
						 .addPackage("net.dubrouski.fams.test.helper")
						 .addClasses(Resources.class, FmsException.class,
								 	 LocalDatePersistenceConverter.class,
								 	 EntityValidator.class)
						 .addAsResource("META-INF/persistence.xml",
										"META-INF/persistence.xml")
						 .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	@Inject
	AddressDao addressDao;
	
	@Inject
	AccommodationUnitDao unitDao;
	
	@Inject
	PriceDao priceDao;
	
	@Inject
	Logger log;
	
	@Inject
	AddressTestHelper addressHelper;
	
	@Inject
	PriceTestHelper priceHelper;
	
	private Place getPlace(boolean isActive, String name, BigDecimal deposit){
		Place place = new Place();
		place.setIsActive(isActive);
		place.setName(name);
		place.setDepositAmount(deposit);
		return place;
	}
	
	private Room getRoom(boolean isActive, String name, BigDecimal deposit){
		Room room = new Room();
		room.setIsActive(isActive);
		room.setName(name);
		room.setDepositAmount(deposit);
		return room;
	}
	
//	Does not work for unknown reason
//	private <T extends AccommodationUnit> T getUnit(
//											Class<T> klass,
//											boolean isActive,
//											String name,
//											BigDecimal deposit){
//		T unit = null;
//		try{
//			Constructor<T> constructor = klass.getConstructor();		
//			unit = (T) constructor.newInstance();
//			unit.setIsActive(isActive);
//			unit.setDepositAmount(deposit);
//			unit.setName(name);
//		}
//		catch(Exception ex){
//			throw new FmsException("Exception occured in test:", ex);
//		}		
//		return unit;		
//	}
	
	@Test
	public void createTest(){
		AccommodationUnit place = getPlace(false, "place by window", BigDecimal.valueOf(4321.50));		
		int unitCount = unitDao.listAll().size();
		unitDao.save(place);
		assertEquals(unitCount + 1, unitDao.listAll().size());
	}
	
	@Test
	public void addChildTest(){
		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
		unitDao.save(place);

		Room room = getRoom(false, "nice room", BigDecimal.valueOf(55.24));
		unitDao.save(room);
		assertEquals(0, room.getChildren().size());
		unitDao.addChild(room, place);
		assertEquals(1, room.getChildren().size());
	}
	
	@Test
	public void removeChildTest(){
		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
		unitDao.save(place);

		Room room = getRoom(false, "nice room", BigDecimal.valueOf(55.24));
		unitDao.save(room);
		assertEquals(0, room.getChildren().size());
		unitDao.addChild(room, place);
		assertEquals(1, room.getChildren().size());
		unitDao.removeChild(room, place);
	}
	
	@Test
	public void getParentTest(){
		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
		unitDao.save(place);
		Room room = getRoom(false, "nice room", BigDecimal.valueOf(55.24));
		unitDao.save(room);		
		unitDao.addChild(room, place);
		
		assertEquals(unitDao.getParent(place), room);
		
	}
	
	@Test
	public void setAddressTest(){
		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
		unitDao.save(place);
		
		Address a = addressHelper.getTestAddress("Brno", "botanicka", "63a", "2b");
		addressDao.save(a);
		assertNotNull(a.getId());
		assertNull(place.getAddress());
		unitDao.setAddress(place, a);
		assertNotNull(place.getAddress());
	}
	
//	uncomment to see fail - hibernate tries to insert duplicate keys into association table
//	on update for unknown reason
//	@Test
//	public void setAddressWithChildrenTest(){
//		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
//		unitDao.save(place);
//		
//		Address a = addressHelper.getTestAddress("Brno", "botanicka", "63a", "2b");
//		addressDao.save(a);		
//				
//		assertNull(place.getAddress());
//		
//		Room room = getRoom(false, "nice room", BigDecimal.valueOf(55.24));
//		unitDao.save(room);
//		
//		unitDao.addChild(room, place);
//		
//		unitDao.setAddressWithChildren(room, a);
//		
//		assertNotNull(room.getAddress());
//	}
	
	@Test
	public void setPriceTest(){
		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
		unitDao.save(place);
		
		Price price = priceHelper.getTestPrice(BigDecimal.valueOf(500),
											   BigDecimal.valueOf(200),
											   LocalDate.of(2014, 6, 4),
											   LocalDate.of(2015, 6, 4),
											   "EUR");
		priceDao.save(price);
		assertNull(place.getPrice());
		unitDao.setPrice(place, price);
		assertNotNull(place.getPrice());
	}

//	same case as setAddressWithChildrenTest()
//	@Test
//	public void setPriceForParentTest(){
//		Place place = getPlace(false, "my place", BigDecimal.valueOf(55.24));
//		unitDao.save(place);
//		
//		Price price = priceHelper.getTestPrice(BigDecimal.valueOf(500),
//											   BigDecimal.valueOf(200),
//											   LocalDate.of(2014, 6, 4),
//											   LocalDate.of(2015, 6, 4),
//											   "EUR");
//		priceDao.save(price);
//		
//		Room room = getRoom(false, "nice room", BigDecimal.valueOf(55.24));
//		unitDao.save(room);		
//		unitDao.addChild(room, place);
//		
//		assertNull(room.getPrice());
//		unitDao.setPrice(room, price);
//		assertNotNull(room.getPrice());
//	}
	
}
