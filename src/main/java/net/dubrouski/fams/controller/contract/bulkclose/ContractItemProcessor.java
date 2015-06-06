package net.dubrouski.fams.controller.contract.bulkclose;

import java.time.LocalDate;
import java.util.logging.Logger;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.service.ContractService;

@Named
public class ContractItemProcessor implements ItemProcessor {

	@Inject
	ContractService contractService;

	@Inject
	Logger logger;

	@Override
	public Object processItem(Object arg) throws Exception {

		Contract contract = (Contract) arg;

		logger.info("Got contract " + contract.getId() + ", "
				+ contract.getCode() + " to process.");

		// TODO add complete logic to evaluate if
		// contract fulfills conditions to be closed.

		if (contract.getState().equals(ContractState.Signed)
				&& LocalDate.now().isAfter(contract.getEndDate())) {
			logger.info("Contract " + contract.getId() + ", "
					+ contract.getCode()
					+ " is in Signed state, pass to futher processing.");
			return contract;
		}

		logger.info("Contract " + contract.getId() + ", " + contract.getCode()
				+ " not in signed state. Not passing for processing.");

		return null;
	}
}
