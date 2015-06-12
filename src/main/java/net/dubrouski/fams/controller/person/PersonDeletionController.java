package net.dubrouski.fams.controller.person;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import net.dubrouski.fams.model.Person;
import net.dubrouski.fams.service.ContractService;
import net.dubrouski.fams.service.PersonService;

/**
 * @author stanislau.dubrouski
 *
 */
@ManagedBean
@SessionScoped
public class PersonDeletionController {

	@Inject
	private Logger logger;

	@Inject
	PersonService personService;

	@Inject
	ContractService contractService;

	private Person personForDeletion;

	private int numberOfContracts;

	private boolean deletionAllowed;

	@Produces
	@Named
	public Person getPersonForDeletion() {
		return personForDeletion;
	}

	public String requestPersonDelete(Person person) {

		this.personForDeletion = person;
		if (personForDeletion == null) {
			logger.log(Level.WARNING,
					"requestPersonDelete: personForDeletion is null.");
		}

		this.numberOfContracts = contractService.getContractsByPerson(
				this.personForDeletion).size();

		this.deletionAllowed = numberOfContracts == 0 ? true : false;

		return "delete";
	}

	public String confirmPersonDelete() {

		if (personForDeletion == null) {
			logger.log(Level.WARNING,
					"confirmPersonDelete: personForDeletion is null.");
		}

		if (!deletionAllowed) {
			logger.log(Level.WARNING,
					"Deletion confirmed for person with not allowed deletion!"
							+ personForDeletion.toString());

			//TODO wrap into separate method
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"Person "
									+ this.personForDeletion.getFullName() + " cannot be deleted.",
							"Person couldn't be deleted."));
			FacesContext.getCurrentInstance().getExternalContext().getFlash()
					.setKeepMessages(true);

			return "delete";
		}

		personService.delete(personForDeletion);
		
		//TODO wrap into separate method
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO,
						"Person "
								+ this.personForDeletion.getFullName() + " successfully deleted.",
						"Person deleted."));
		FacesContext.getCurrentInstance().getExternalContext().getFlash()
				.setKeepMessages(true);
		
		return "list";
	}

	public int getNumberOfContracts() {
		return numberOfContracts;
	}

	public boolean isDeletionAllowed() {
		return deletionAllowed;
	}
	
	
}
