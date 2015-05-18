package net.dubrouski.fams.controller.contract;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Contract;
import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.model.enums.ContractState;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.service.ContractService;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class ContractCreationController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	@Inject
	AccommodationUnitService accommService;

	@Inject
	ContractService contractService;

	private Contract newContract;
	
	private Price newPrice;

	private List<Person> personsList;

	public Contract getNewContract() {
		return newContract;
	}
	
	public Price getNewPrice(){
		return newPrice;
	}

	public List<Person> getPersonsList() {
		return this.personsList;
	}

	public List<AccommodationUnit> getAccommList() {
		return accommService.listAccommodations();
	}

	public String createContract() {
		logger.info("Saving new contract " + newContract.toString());

		contractService.saveContract(newContract, newPrice);

		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Contract created successufully.", null));
		initNewContract();
		return "list";
	}

	@PostConstruct
	public void initNewContract() {
		newContract = new Contract();
		newContract.setState(ContractState.New);
		personsList = personService.listPersons();
	}
}
