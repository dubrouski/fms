package net.dubrouski.fams.controller.contract.bulkclose;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSContext;
import javax.jms.Queue;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.service.ContractService;

@Named
public class ContractItemWriter extends AbstractItemWriter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ContractService contractService;

	@Inject
	Logger logger;

	@Resource(mappedName = "java:/jms/queue/contractQueue")
	private Queue contractQueue;

	@Inject
	JMSContext context;

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

			try {

				context.createProducer().send(contractQueue, contract);
				logger.info("Sending message to queue ContractQueue " + contract.toString());

			} catch (Exception exc) {
				exc.printStackTrace();
			}
		}
	}
}
