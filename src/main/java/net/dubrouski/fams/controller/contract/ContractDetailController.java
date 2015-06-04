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
import javax.print.attribute.standard.Severity;

import net.dubrouski.fams.controller.ControllerHelper;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.MeterRecord;
import net.dubrouski.fams.model.enums.ContractState;
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

	public String handoverKeys() {
		if (contractService.handoverKeys(this.contract)) {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_INFO,
					"Keys have been handed over for contract " + contract.getCode() + ".",
					true);
		} else {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_WARN,
					"Keys could not be handed over for contract " + contract.getCode() + ".",
					true);
		}

		return "contract-detail?faces-redirect=true";

	}

	public String signContract() {
		if (contractService.signContract(this.contract)) {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_INFO,
					"Contract " + contract.getCode() + " has been signed.",
					true);
		} else {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_WARN,
					"Contract " + contract.getCode() + " could not be signed.",
					true);
		}

		return "contract-detail?faces-redirect=true";
	}

	public String cancelContract() {
		if (contractService.cancelContract(this.contract)) {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_INFO,
					"Contract " + contract.getCode() + " has been cancelled.",
					true);
		} else {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_WARN,
					"Contract " + contract.getCode() + " could not be canceled.",
					true);
		}

		return "contract-detail?faces-redirect=true";
	}

	public String closeContract() {
		if (contractService.closeContract(this.contract)) {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_INFO,
					"Contract " + contract.getCode() + " has been closed.",
					true);
		} else {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_WARN,
					"Contract " + contract.getCode() + " could not be closed.",
					true);
		}
		
		return "contract-detail?faces-redirect=true";
	}

	public String createTerminationRequest() {
		if (contractService.createTerminationRequest(this.contract)) {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_INFO,
					"Termination request created for contract " + contract.getCode() + ".",
					true);
		} else {
			ControllerHelper.addInfoMessage(FacesMessage.SEVERITY_WARN,
					"Termination request could not be created for contract " + contract.getCode() + ".",
					true);
		}

		return "contract-detail?faces-redirect=true";
	}

	public boolean isKeysHandoverAllowed() {
		return this.contract.getState().equals(ContractState.Signed);
	}

	public boolean isContractSigningAllowed() {
		return this.contract.getState().equals(ContractState.New);
	}

	public boolean isContractCancellationAllowed() {
		return this.contract.getState().equals(ContractState.New);
	}

	public boolean isContractClosureAllowed() {
		return this.contract.getState().equals(ContractState.Signed);
	}

	public boolean isTerminationRequestCreationAllowed() {
		return this.contract.getState().equals(ContractState.Signed);
	}

}
