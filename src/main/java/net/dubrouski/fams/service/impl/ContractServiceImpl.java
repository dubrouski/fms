package net.dubrouski.fams.service.impl;

import net.dubrouski.fams.dao.AddressDao;
import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.dao.PersonDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.model.enums.SortingOrder;
import net.dubrouski.fams.service.ContractService;

import javax.inject.Inject;
import javax.inject.Named;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by tmarton on 5/2/15.
 */
@Named(value = "contractService")
public class ContractServiceImpl implements ContractService {

	@Inject
	ContractDao contractDao;
	
	@Inject
	PriceDao priceDao;

	@Inject
	Logger logger;

	@Override
	public Contract getContractById(Long id) {
		logger.info("Retrieving Contract by id:" + id);
		return contractDao.getByID(id);
	}

	@Override
	public void saveContract(Contract contract, Price price) {
		logger.info("Saving Contract");
		if (contract.getId() != null) {
			throw new FmsException("Cannot save: already existing entity: "
					+ contract);
		}
		priceDao.save(price);
		contract.setPrice(price);
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
		logger.info("Retrieving all Contracts by Person with id:"
				+ person.getId());
		return contractDao.getContractsByPerson(person);
	}

	@Override
	public List<Contract> getContractsByAccommodationUnit(
			AccommodationUnit accommodationUnit) {
		logger.info("Retrieving all Contracts by Address with id:"
				+ accommodationUnit.getId());
		return contractDao.getContractsByAccommodationUnit(accommodationUnit);
	}

	@Override
	public List<Contract> listContracts(int pageSize, int first,
			String sortField, SortingOrder sortingOrder, String searchTerm) {
		logger.info(String.format("Listing for %d, %d, %s %s", pageSize, first,
				sortField, sortingOrder.name()));

		return contractDao.listContracts(pageSize, first, sortField,
				sortingOrder, searchTerm);
	}

	@Override
	public long getContractsCount() {
		return contractDao.getContractsCount();
	}

	@Override
	public void handoverKeys(Contract contract) {
		logger.info("Keys handover requested for contract: "
				+ contract.getCode());
		contract.setKeysHandedOver(true);
		contract.setKeysHandoverDate(LocalDate.now());
		this.updateContract(contract);
		logger.info("Keys handover set for contract: " + contract.getCode()
				+ " to date " + contract.getKeysHandoverDate());
	}

	@Override
	public void cancelContract(Contract contract) {
		// TODO complete implementation
		// TODO create tests
		logger.info("Cancellation requested for contract: "
				+ contract.getCode());
		contract.setState(ContractState.Cancelled);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been cancelled.");
	}

	@Override
	public void signContract(Contract contract) {
		// TODO complete implementation
		// TODO create tests
		logger.info("Signing requested for contract: " + contract.getCode());
		contract.setState(ContractState.Signed);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been signed.");

	}

	@Override
	public void closeContract(Contract contract) {
		// TODO complete implementation
		// TODO create tests
		logger.info("Closing requested for contract: " + contract.getCode());
		contract.setState(ContractState.Closed);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been closed.");

	}
}
