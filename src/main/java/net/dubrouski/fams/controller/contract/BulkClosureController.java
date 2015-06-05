package net.dubrouski.fams.controller.contract;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.annotation.Resource;
//import javax.ejb.Stateless;
//import javax.enterprise.concurrent.ManagedExecutorService;
import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/bulkclosure")
@ManagedBean
@SessionScoped
public class BulkClosureController {
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
			Thread.sleep(1000);
			session.getBasicRemote().sendText(
					"This is an intermediate server message. Count: "
							+ sentMessages);
			sentMessages++;
		}

		// Send a final message to the client
		session.getBasicRemote().sendText("This is the last server message");

	}

	@OnOpen
	public void open(Session session) {
		logger.info("Open session:" + session.getId());
	}

	@OnClose
	public void close(Session session, CloseReason c) {
		logger.info("Closing:" + session.getId());
	}

	public long startNewBatchJob() throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		// props.setProperty("payrollInputDataFileName",
		// payrollInputDataFileName);
		return jobOperator.start("ContractsClosureJob", props);
	}

}
