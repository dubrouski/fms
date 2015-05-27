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
import net.dubrouski.fams.service.MeterRecordingService;
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

	@Inject
	MeterRecordingService meterRecordingService;

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
		if (contractService.handoverKeys(this.contract)) {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Keys for contract " + contract.getCode()
							+ " have been handed over."));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Keys for contract " + contract.getCode()
							+ "could not be handed over.", ""));
		}
	}

	public void signContract() {
		if (contractService.signContract(this.contract)) {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Contract " + contract.getCode()
							+ "has been signed."));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Contract " + contract.getCode()
							+ "could not be signed.", ""));
		}
	}

	public void cancelContract() {
		if (contractService.cancelContract(this.contract)) {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Contract " + contract.getCode()
							+ "has been cancelled."));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Contract "
							+ contract.getCode() + "could not be cancelled.", ""));
		}
	}

	public void closeContract() {
		if (contractService.closeContract(this.contract)) {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage("Contract " + contract.getCode()
							+ "has been closed."));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Contract "
							+ contract.getCode() + "could not be closed.", ""));
		}

	}

	public void createTerminationRequest() {
		if (contractService.createTerminationRequest(this.contract)) {
			FacesContext
					.getCurrentInstance()
					.addMessage(
							"",
							new FacesMessage(
									"Termination request for contract has been created on "
											+ this.contract
													.getTerminationRequestDate()));
		} else {
			FacesContext.getCurrentInstance().addMessage(
					"",
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							"Error occurred when creating termination request for contract "
									+ this.contract.getCode(), ""));
		}

	}	
}
