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

		// Send the first message to the client
		session.getBasicRemote().sendText("This is the first server message");

		// Send 3 messages to the client every 5 seconds
		int sentMessages = 0;
		while (sentMessages < 10) {
			Thread.sleep(100);
			session.getBasicRemote().sendText(
					"EP2: This is an intermediate server message. Count: "
							+ sentMessages);
			// this.send("Message sent from send() method");
			// logger.info("Sent intermediate message to client");
			sentMessages++;
		}

		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");
	}

	@OnOpen
	public void open(Session session) {
		this.sess = session;
		try {
			session.getBasicRemote().sendText("Backend: session established.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Open session:" + session.getId());
	}

	@OnClose
	public void close(Session session, CloseReason c) {
		logger.info("Closing:" + session.getId());
	}

	public void send(String msg) {
		try {
			if (this.sess == null) {
				logger.info("SESSION IS NULL!");
			}
			this.sess.getBasicRemote().sendText(msg);
		} catch (IOException e) {
			logger.info("!!! IOException in sending to websocket.");
		} catch (Exception e) {
			logger.info("!!! Exception in sending to websocket.");
		}
	}

	public void onEvent(
			@Observes @ContractEvent ContractStateChangeEvent event) {
		logger.info("Contract changed state event observed: "
				+ event.getContract().getCode());
		this.send(event.getContract().toString());
	}

}
