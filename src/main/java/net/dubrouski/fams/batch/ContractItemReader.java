package net.dubrouski.fams.batch;

import java.io.Serializable;
import java.util.logging.Logger;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.service.ContractService;

@Named
public class ContractItemReader extends AbstractItemReader {

	private static long MIN_ID = 1;
	private static long INCREMENT_BY = 1;

	@Inject
	ContractService contractService;

	@Inject
	Logger logger;

	private long lastProcessedId = -1;

	@Override
	public Object readItem() throws Exception {
		logger.info("Reading item for batch processing... last processed id is "
				+ lastProcessedId);

		lastProcessedId = lastProcessedId < 0 ? MIN_ID : lastProcessedId
				+ INCREMENT_BY;

		logger.info("Last processed id set to " + lastProcessedId
				+ ". Now reading contract with id " + lastProcessedId + "...");

		Contract result = contractService.getContractById(lastProcessedId);

		Thread.sleep(1000);

		if (result == null) {
			logger.info("Contract with id " + lastProcessedId
					+ " not found. Stopping batch execution...");

			// throw new ItemNotFoundException("Contract with id "
			// + lastProcessedId + " not found.");

			return null;
		}

		logger.info("Contract " + result.getId() + ", " + result.getCode()
				+ " found. Return result.");

		return result;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		// TODO Implement check point of last succ. read.
		return super.checkpointInfo();
	}
}
