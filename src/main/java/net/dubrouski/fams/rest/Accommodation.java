package net.dubrouski.fams.rest;

import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.service.AccommodationUnitService;

@Path("/accommodations")
@Produces("application/json")
@Consumes("application/json")
public class Accommodation{
	
	@Inject
	AccommodationUnitService unitService;
	
	@Inject
	Logger logger;
	
	@GET	
	public List<AccommodationUnit> listAccommodations(){
		return unitService.listAccommodations();
	}
	
	@GET
	@Path("/{id}")
//	@Produces("application/json")
	public AccommodationUnit getAccommodationByID(@PathParam("id") Long id){
		return unitService.getAccommodationById(id); 
	}
	
	@PUT
//	TODO: check for invalid children on update in dao layer?
	
	public void updateAccommodation(AccommodationUnit unit){
		unitService.update(unit);
	}
	
	@POST
	public void saveAccommodation(AccommodationUnit unit){
		logger.info(unit.toString());
//		unitService.save(unit);	
	}
	
	@DELETE
	public void deleteAccommodation(AccommodationUnit unit){
		logger.info(unit.toString());
//		unitService.delete(unit);
	}
}
