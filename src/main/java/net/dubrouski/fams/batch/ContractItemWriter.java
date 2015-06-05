package net.dubrouski.fams.batch;

import java.util.List;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.service.ContractService;

@Named
public class ContractItemWriter extends AbstractItemWriter {

	@Inject
	ContractService contractService;

	@Inject
	Logger logger;

	@Override
	public void writeItems(List<Object> contracts) throws Exception {

		for (Object obj : contracts) {
			if (obj == null) {
				continue;
			}

			Contract contract = (Contract) obj;
			logger.info("Got contract " + contract.getId() + ", "
					+ contract.getCode() + " to write.");
			contractService.closeContract(contract);
		}
	}
}
