package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.rest.CurrencyConversionClient;
import net.dubrouski.fams.service.ContractService;
import net.dubrouski.fams.service.CurrencyService;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class ContractDetailController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	@Inject
	ContractService contractService;

	@Inject
	CurrencyService currencyService;

	private Contract contract;

	private LocalDate terminationRequestDate;

	public String showDetail(Contract c) {
		this.contract = c;
		return "contract-detail?faces-redirect=true";
	}

	public Contract getContract() {
		return contract;
	}
	
	public void setTerminationRequestDate(LocalDate terminationRequestDate) {
		this.terminationRequestDate = terminationRequestDate;
	}
	
	public LocalDate getTerminationRequestDate() {
		return terminationRequestDate;
	}
	
	public void handoverKeys() {
		contractService.handoverKeys(this.contract);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Keys handed over."));
	}

	public void signContract() {
		contractService.signContract(this.contract);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Contract has been successfully signed."));
	}

	public void cancelContract() {
		contractService.cancelContract(this.contract);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Contract has been successfully cancelled."));
	}

	public void closeContract() {
		contractService.closeContract(this.contract);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Contract has been successfully closed."));
	}

	public void createTerminationRequest() {
		contractService.createTerminationRequest(this.contract);
		FacesContext.getCurrentInstance().addMessage(
				"",
				new FacesMessage(
						"Termination request for contract has been created on " + this.contract.getTerminationRequestDate()));
	}
	
	public Set<MeterRecord> getStartRecords() {
		return contractService.getStartMeterRecordsForContract(this.contract);
	}

	public Set<MeterRecord> getEndRecords() {
		return contractService.getEndMeterRecordsForContract(this.contract);
	}
}
