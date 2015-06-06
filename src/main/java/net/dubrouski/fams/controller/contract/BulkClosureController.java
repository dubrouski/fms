package net.dubrouski.fams.controller.contract;

import java.util.Properties;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.websocket.Session;

@ManagedBean
@SessionScoped
public class BulkClosureController {

	private Session s;

	@Inject
	Logger logger;

	public long startNewBatchJob() throws Exception {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		return jobOperator.start("ContractsClosureJob", props);
	}
}
