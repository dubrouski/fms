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
import javax.ws.rs.core.Response;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.model.AccommodationComposite;
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
	public Response listAccommodations(){
		try{
			List<AccommodationUnit> found = unitService.listAccommodations();
			return Response.ok().entity(found).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getAccommodationByID(@PathParam("id") Long id){
		try{
			AccommodationUnit found = unitService.getAccommodationById(id);
			return Response.ok().entity(found).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@PUT
//	TODO: check for invalid children on update
	public Response updateAccommodation(AccommodationUnit unit){
		try{
			unitService.update(unit);
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
		return Response.ok(unit).build();
	}
	
	@POST
	public Response saveAccommodation(AccommodationUnit unit){
		try{
			unitService.save(unit);
			return Response.ok(unit).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
			
	}
	
	@DELETE
	public Response deleteAccommodation(AccommodationUnit unit){
		try{
			unitService.delete(unit);
			return Response.ok().entity(unit).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}		
	}
	
	@GET
	@Path("/{types}")
	public Response listAccommodationsByType(@PathParam("types") String types){
		try{
			String type = types.substring(0, types.length() - 1);
			List<AccommodationUnit> found = unitService.listAccommodationsByType(type);
			return Response.ok().entity(found).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
}
