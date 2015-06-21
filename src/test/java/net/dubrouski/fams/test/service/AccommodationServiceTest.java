package net.dubrouski.fams.test.service;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Place;
import net.dubrouski.fams.model.Room;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.test.helper.AccommodationTestHelper;
import net.dubrouski.fams.test.helper.AddressTestHelper;
import net.dubrouski.fams.test.helper.PersonTestHelper;
import net.dubrouski.fams.test.helper.PriceTestHelper;
import net.dubrouski.fams.test.runas.AccommAdminRunAs;
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
public class AccommodationServiceTest {
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap
				.create(WebArchive.class, "test.war")
				.addPackage("net.dubrouski.fams.model")
				.addPackage("net.dubrouski.fams.dao")
				.addPackage("net.dubrouski.fams.dao.impl")
				.addPackage("net.dubrouski.fams.model.enums")
				.addPackage("net.dubrouski.fams.exception")
				.addPackage("net.dubrouski.fams.service")
				.addPackage("net.dubrouski.fams.service.impl")
				.addPackage("net.dubrouski.fams.filter")
				.addPackage("net.dubrouski.fams.rest")
				.addPackage("net.dubrouski.fams.test.runas")
				.addClasses(Resources.class,
						LocalDatePersistenceConverter.class,
						PersonTestHelper.class, AddressTestHelper.class,
						PriceTestHelper.class, AccommodationTestHelper.class)
				.addAsResource("META-INF/persistence.xml",
						"META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Inject
	AccommodationUnitService accommodationService;

	@Inject
	AccommodationTestHelper accommodationHelper;

	@Inject
	AccommAdminRunAs admin;

	@Test
	public void listAll() throws Exception {
		admin.call(new Callable<AccommodationUnit>() {
			@Override
			public AccommodationUnit call() throws Exception {

				int initial = accommodationService.listAccommodations().size();
				Place p = accommodationHelper.getPlace(false, "minor place",
						BigDecimal.valueOf(568.4));
				accommodationService.save(p);
				Place r = accommodationHelper.getPlace(false,
						"I like this space", BigDecimal.valueOf(568.4));
				accommodationService.save(r);
				Place s = accommodationHelper.getPlace(false, "major place",
						BigDecimal.valueOf(568.4));
				accommodationService.save(s);

				List<AccommodationUnit> found = accommodationService
						.listAccommodations();

				assertEquals(initial + 3, found.size());
				assertTrue(found.contains(p));

				return null;
			}
		});
	}

	@Test
	public void listByType() throws Exception {
		admin.call(new Callable<AccommodationUnit>() {
			@Override
			public AccommodationUnit call() throws Exception {
				int initialPlaces = accommodationService
						.listAccommodationsByType("place").size();
				Place p = accommodationHelper.getPlace(false, "minor place",
						BigDecimal.valueOf(568.4));
				accommodationService.save(p);
				Place r = accommodationHelper.getPlace(false,
						"I like this space", BigDecimal.valueOf(568.4));
				accommodationService.save(r);

				int initialRooms = accommodationService
						.listAccommodationsByType("room").size();
				Room s = accommodationHelper.getRoom(false, "major place",
						BigDecimal.valueOf(568.4));
				accommodationService.save(s);

				List<AccommodationUnit> foundPlaces = accommodationService
						.listAccommodationsByType("place");
				List<AccommodationUnit> foundRooms = accommodationService
						.listAccommodationsByType("room");

				assertEquals(initialPlaces + 2, foundPlaces.size());
				assertTrue(foundPlaces.contains(p));

				assertEquals(initialRooms + 1, foundRooms.size());
				assertTrue(foundRooms.contains(s));

				return null;
			}
		});
	}
}
