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
	
	@Inject
	private AccommodationDetailController detailController;
	
	@Produces
	public AccommodationComposite getParent(){
		return parent;
	}
	
	public List<String> typesList(){
		return parent.addmissibleChildrenTypes();
	}
	
	@Override
	public String createAccommodation(){
		accommodationService.createNewChild(parent, converter.ViewModel2Unit(newAccommodation));
		return detailController.showDetail(parent);
	}
	
	public String start(AccommodationComposite p, AccommodationUnit child){
		init();
		parent = p;
		return "accommodation-child";
	}

}
