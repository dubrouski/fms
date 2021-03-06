package net.dubrouski.fams.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.dao.ContractDao;
import net.dubrouski.fams.dao.MeterRecordDao;
import net.dubrouski.fams.dao.PriceDao;
import net.dubrouski.fams.exception.FmsException;
import net.dubrouski.fams.filter.SearchFilter;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.model.enums.SortingOrder;
import net.dubrouski.fams.service.ContractService;

/**
 * Created by tmarton on 5/2/15.
 */
@Named(value = "contractService")
@Stateful
@RolesAllowed("contractAdmin")
@TransactionManagement(TransactionManagementType.CONTAINER)
public class ContractServiceImpl implements ContractService {

	@Inject
	ContractDao contractDao;

	@Inject
	PriceDao priceDao;

	@Inject
	MeterRecordDao meterRecordDao;

	@Inject
	Logger logger;

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public Contract getContractById(Long id) {
		logger.info("Retrieving Contract by id:" + id);
		return contractDao.getByID(id);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
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
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void updateContract(Contract contract) {
		logger.info("Updating Contract with id:" + contract.getId());
		contractDao.update(contract);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void deleteContract(Contract contract) {
		logger.info("Deleting Contract with id:" + contract.getId());
		contractDao.delete(contract);
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public List<Contract> listContracts() {
		logger.info("Retrieving all Contracts.");
		return contractDao.listAll();
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser", "client" })
	public List<Contract> getContractsByPerson(Person person) {
		logger.info("Retrieving all Contracts by Person with id:"
				+ person.getId());
		return contractDao.getContractsByPerson(person);
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public List<Contract> getContractsByAccommodationUnit(
			AccommodationUnit accommodationUnit) {
		logger.info("Retrieving all Contracts by Address with id:"
				+ accommodationUnit.getId());
		return contractDao.getContractsByAccommodationUnit(accommodationUnit);
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public List<Contract> listContracts(int pageSize, int first,
			String sortField, SortingOrder sortingOrder,
			Set<SearchFilter> filters) {
		logger.info(String.format("Listing for %d, %d, %s %s", pageSize, first,
				sortField, sortingOrder.name()));

		return contractDao.list(pageSize, first, sortField, sortingOrder,
				filters);
	}

	@Override
	@RolesAllowed({ "contractAdmin", "contractUser" })
	public long getContractsCount(Set<SearchFilter> filters) {
		return contractDao.getCount(filters);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean handoverKeys(Contract contract) {
		logger.info("Keys handover requested for contract: "
				+ contract.getCode());

		if (!validateContractState(contract,
				Arrays.asList(ContractState.Signed))) {
			return false;
		}

		contract.setKeysHandedOver(true);
		contract.setKeysHandoverDate(LocalDate.now());
		this.updateContract(contract);

		logger.info("Keys handover set for contract: " + contract.getCode()
				+ " to date " + contract.getKeysHandoverDate());
		return true;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean cancelContract(Contract contract) {
		// TODO create tests
		logger.info("Cancellation requested for contract: "
				+ contract.getCode());

		if (!validateContractState(contract, Arrays.asList(ContractState.New))) {
			return false;
		}

		contract.setState(ContractState.Cancelled);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been cancelled.");
		return true;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean signContract(Contract contract) {
		// TODO complete implementation
		// TODO create tests
		logger.info("Signing requested for contract: " + contract.getCode());

		if (!validateContractState(contract, Arrays.asList(ContractState.New))) {
			return false;
		}

		contract.setState(ContractState.Signed);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been signed.");
		return true;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean closeContract(Contract contract) {
		// TODO create tests
		logger.info("Closing requested for contract: " + contract.getCode());

		if (!validateContractState(contract,
				Arrays.asList(ContractState.Signed))) {
			return false;
		}

		contract.setState(ContractState.Closed);
		this.updateContract(contract);
		logger.info("Contract: " + contract.getCode() + " has been closed.");
		return true;
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean createTerminationRequest(Contract contract) {
		// TODO complete implementation
		// TODO create tests
		logger.info("Termination request requested for contract: "
				+ contract.getCode());

		if (!validateContractState(contract,
				Arrays.asList(ContractState.Signed))) {
			return false;
		}

		contract.setTerminationRequestDate(LocalDate.now());
		this.updateContract(contract);
		logger.info("Termination request for contract: " + contract.getCode()
				+ " has been set (date " + contract.getTerminationRequestDate());
		return true;
	}

	private boolean validateContractState(Contract contract,
			List<ContractState> statesAllowed) {
		if (!statesAllowed.contains(contract.getState())) {
			String resultMessage = "Contract "
					+ contract.getCode()
					+ " is not in allowed states! Requested operation requires contract to be in on of the following states: ";
			for (ContractState state : statesAllowed) {
				resultMessage.concat(state.toString() + "; ");
			}
			logger.info(resultMessage);
			return false;
		} else {
			return true;
		}
	}
}
