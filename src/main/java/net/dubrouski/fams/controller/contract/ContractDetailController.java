package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.dubrouski.fams.model.Contract;
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

	public String showDetail(Contract c) {
		this.contract = c;

		// example sanity check, will be removed
		logger.info(currencyService.recalculate("USD", "CZK",
				BigDecimal.valueOf(1)).toString());
		return "detail?faces-redirect=true";

	}
	
	public Contract getContract() {
		return contract;
	}

	public void handoverKeys() {
		contractService.handoverKeys(this.contract);
		FacesContext.getCurrentInstance().addMessage("",
				new FacesMessage("Keys handed over."));
	}
}
