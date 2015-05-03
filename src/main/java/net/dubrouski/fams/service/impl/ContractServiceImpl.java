package net.dubrouski.fams.service.impl;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.ContractService;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tmarton on 5/2/15.
 */
@Named(value = "contractService")
public class ContractServiceImpl  implements ContractService {

    @Inject
    ContractDao contractDao;

    @Inject
    Logger logger;

    @Override
    public Contract getContractById(Long id) {
        logger.info("Retrieving Contract by id:" + id);
        return contractDao.getByID(id);
    }

    @Override
    public void saveContract(Contract contract) {
        logger.info("Saving Contract");
        if(contract.getId() != null){
            throw new FmsException("Cannot save: already existing entity: " + contract);
        }
        contractDao.save(contract);
        logger.info("Saved Contract with id:" + contract.getId());
    }

    @Override
    public void updateContract(Contract contract) {
        logger.info("Updating Contract with id:" + contract.getId());
        contractDao.update(contract);
    }

    @Override
    public void deleteContract(Contract contract) {
        logger.info("Deleting Contract with id:" + contract.getId());
        contractDao.delete(contract);
    }

    @Override
    public List<Contract> listContracts() {
        logger.info("Retrieving all Contracts.");
        return contractDao.listAll();
    }

    @Override
    public List<Contract> getContractsByPerson(Person person) {
        logger.info("Retrieving all Contracts by Person with id:" + person.getId());
        return contractDao.getContractsByPerson(person);
    }

    @Override
    public List<Contract> getContractsByAccommodationUnit(AccommodationUnit accommodationUnit) {
        logger.info("Retrieving all Contracts by Address with id:" +accommodationUnit.getId());
        return contractDao.getContractsByAccommodationUnit(accommodationUnit);
    }
}
