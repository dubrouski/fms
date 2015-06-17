package net.dubrouski.fams.controller.contract.endpoint;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.dubrouski.fams.annotations.ContractEvent;
import net.dubrouski.fams.controller.contract.bulkclose.ContractBulkClosureFinishedEvent;
import net.dubrouski.fams.controller.contract.bulkclose.ContractStateChangeEvent;

@ServerEndpoint("/bulkclosure")
@Singleton
public class ContractEndpoint implements Serializable {

	private static final long serialVersionUID = 1L;

	private Session sess;

	@Inject
	Logger logger;

	@OnMessage
	public void receiveMessage(String message, Session session)
			throws IOException, InterruptedException {
		logger.info("Received : " + message + ", session:" + session.getId());
	}

	@OnOpen
	public void open(Session session) {
		this.sess = session;
		try {
			session.getBasicRemote().sendText("Session established...");
		} catch (IOException e) {
			e.printStackTrace();
		}
		logger.info("Open session:" + session.getId());
	}

	@OnClose
	public void close(Session session, CloseReason c) {
		logger.info("Closing session...");
	}

	public void send(String msg) {
		try {
			if (this.sess == null) {
				logger.info("session is null");
			}
			this.sess.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			logger.info("IOException in sending to websocket." + e.getCause());
		} catch (Exception e) {
			logger.info("General Exception in sending to websocket."
					+ e.getCause());
		}
	}

	public void onEvent(@Observes @ContractEvent ContractStateChangeEvent event) {
		logger.info("Contract changed state event observed: "
				+ event.getContract().getCode());
		this.send(event.getContract().toString());
	}

	public void onEvent(
			@Observes @ContractEvent ContractBulkClosureFinishedEvent event) {
		logger.info("Bulk assignment process finished event received. Informing client...");
		this.send("Process of bulk closure just finished.");
	}

}
