package net.dubrouski.fams.controller.accommodation;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;

@ManagedBean
@SessionScoped
public class AccChildController extends AccommodationCreationController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private AccommodationComposite parent;
	
	private List<String> typesList;
	
	@Inject
	private AccommodationDetailController detailController;
	
	@Produces
	public AccommodationComposite getParent(){
		return parent;
	}
	
	@Produces
	public List<String> getTypesList(){
		return typesList;
	}
	
	@Override
	public String createAccommodation(){
		accommodationService.createNewChild(parent, converter.ViewModel2Unit(newAccommodation));
		init();
		return detailController.showDetail(parent);
	}
	
	public String start(AccommodationComposite p){
		init();
		parent = p;
		typesList = parent.addmissibleChildrenTypes();
		return "accommodation-child";
	}

}
