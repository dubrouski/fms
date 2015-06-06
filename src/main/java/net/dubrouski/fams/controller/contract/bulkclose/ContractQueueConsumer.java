package net.dubrouski.fams.controller.contract.bulkclose;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import net.dubrouski.fams.annotations.ContractEvent;
import net.dubrouski.fams.model.Contract;

@MessageDriven(mappedName = "contractQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "java:/jms/queue/contractQueue"),
		@ActivationConfigProperty(propertyName = "useJNDI", propertyValue = "true") })
public class ContractQueueConsumer implements MessageListener {

	@Inject
	Logger logger;

	@Inject
	@ContractEvent
	Event<ContractStateChangeEvent> contractEvent;

	@Override
	public void onMessage(Message message) {
		try {
			Contract payload = message.getBody(Contract.class);
			logger.info("Received message from ContractQueue: "
					+ payload.toString());

			logger.info("Endpoint is OK, sending message...");
			ContractStateChangeEvent csce = new ContractStateChangeEvent(
					payload);
			contractEvent.fire(csce);

		} catch (JMSException e) {
			logger.log(Level.WARNING, "Error occured on message receive");
		}
	}
}
