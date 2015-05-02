package net.dubrouski.fams.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import net.dubrouski.fams.converter.LocalDatePersistenceConverter;
import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.test.helper.AccommodationTestHelper;
import net.dubrouski.fams.test.helper.ContractTestHelper;
import net.dubrouski.fams.test.helper.PersonTestHelper;
import net.dubrouski.fams.util.Resources;
import net.dubrouski.fams.validator.EntityValidator;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tmarton on 5/2/15.
 */
@RunWith(Arquillian.class)
public class ContractDaoTest {

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
                        EntityValidator.class, AccommodationTestHelper.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Logger log;

    @Inject
    private ContractDao contractDao;

    @Inject
    private PersonDao personDao;

    @Inject
    private AccommodationUnitDao accommodationUnitDao;

    @Inject
    ContractTestHelper helper;

    @Inject
    private PersonTestHelper personTestHelper;

    @Inject
    private AccommodationTestHelper accommodationTestHelper;

    @Before
    public void beforePreparation() {
        personDao.save(personTestHelper.getTestPerson());
        accommodationUnitDao.save(accommodationTestHelper.getPlace(true,"place",new BigDecimal(8000)));
        accommodationUnitDao.save(accommodationTestHelper.getRoom(true,"room",new BigDecimal(2500)));
    }

    @After
    public void afterPreparation() {
        for (Contract contract : contractDao.listAll()) {
            contractDao.delete(contract);
        }

        for (Person person : personDao.listAll()) {
            personDao.delete(person);
        }

        for (AccommodationUnit au :accommodationUnitDao.listAll()) {
            accommodationUnitDao.delete(au);
        }
    }

    @Test
    public void testSave() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));
        contractDao.save(contract);
        assertNotNull(contract.getId());
    }

    @Test(expected = Exception.class)
    public void testSaveNullTenant() {
        contractDao.save(helper.getContract());
    }

    @Test(expected = Exception.class)
    public void testSaveNullAccommodation() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contractDao.save(contract);
    }

    @Test(expected = Exception.class)
    public void testSaveNullStartDate() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));
        contract.setStartDate(null);
        contractDao.save(contract);
    }

    @Test(expected = Exception.class)
    public void testSaveNullState() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));
        contract.setState(null);
        contractDao.save(contract);
    }

    @Test
    public void testListAllContracts() {
        List<Contract> contracts = contractDao.listAll();
        assertEquals(0, contracts.size());

        Person tenant = personDao.listAll().get(0);
        AccommodationUnit room = accommodationUnitDao.listAccommodationsByType("room").get(0);
        AccommodationUnit place = accommodationUnitDao.listAccommodationsByType("place").get(0);

        Contract contractPlace = helper.getContract();
        contractPlace.setTenant(tenant);
        contractPlace.setAccommodationUnit(place);

        Contract contractRoom = helper.getContract();
        contractRoom.setTenant(tenant);
        contractRoom.setAccommodationUnit(room);

        contractDao.save(contractPlace);
        contractDao.save(contractRoom);

        assertEquals(2, contractDao.listAll().size());
    }

    @Test
    public void testGetById() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));
        contractDao.save(contract);

        Contract contractById = contractDao.getByID(contract.getId());
        assertNotNull(contractById);
        assertEquals(contract.getId(), contractById.getId());
    }

    @Test
    public void testUpdate() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));

        contractDao.save(contract);

        contract.setState(ContractState.Signed);
        contract.setKeysHandedOver(true);
        contract.setSignDate(LocalDate.now());

        contractDao.update(contract);

        Contract contractById = contractDao.getByID(contract.getId());

        assertEquals(contract.getStartDate(),contractById.getStartDate());
        assertEquals(contract.getState(), contractById.getState());
        assertEquals(contract.isKeysHandedOver(), contractById.isKeysHandedOver());
    }

    @Test
    public void testDelete() {
        Contract contract = helper.getContract();
        contract.setTenant(personDao.listAll().get(0));
        contract.setAccommodationUnit(accommodationUnitDao.listAll().get(0));
        contractDao.save(contract);

        assertEquals(1, contractDao.listAll().size());

        contractDao.delete(contract);
        assertEquals(0, contractDao.listAll().size());
    }
}
