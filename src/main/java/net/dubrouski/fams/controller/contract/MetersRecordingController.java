package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.model.enums.MeterType;
import net.dubrouski.fams.service.ContractService;

@ManagedBean
@RequestScoped
public class MetersRecordingController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ContractService contractService;
	
	@Inject 
	Logger logger;
	
	MeterRecord record;

	public MeterType[] getMeterTypes() {
		return MeterType.values();
	}
	
	@PostConstruct
	public void initRecord(){
		record = new MeterRecord(); 	
	}
	 
	public void saveStartMeterRecord(Contract contract){			
		logger.info("addStartMetersRecordForContract " + contract.getCode() + " " + record.getMeterType() + " " + record.getValue());
		contractService.addStartMetersRecordForContract(contract, getRecord());
		FacesContext.getCurrentInstance().addMessage("message", new FacesMessage("Meter record added."));
	}
	
	public void saveFinishMeterRecord(Contract contract){	
		logger.info("saveFinishMeterRecord " + contract.getCode() + " " + record.getMeterType() + " " + record.getValue());
		contractService.addFinishMetersRecordForContract(contract, getRecord());
		FacesContext.getCurrentInstance().addMessage("message", new FacesMessage("Meter record added."));
	}
	
	public MeterRecord getRecord(){
		return this.record;
	}
}
