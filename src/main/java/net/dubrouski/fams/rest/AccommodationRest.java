package net.dubrouski.fams.rest;

import java.util.List;
import java.util.logging.Logger;
import javax.annotation.security.RolesAllowed;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.dubrouski.fams.dao.AccommodationUnitDao;
import net.dubrouski.fams.model.AccommodationComposite;
import net.dubrouski.fams.model.AccommodationUnit;
import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.model.Price;
import net.dubrouski.fams.service.AccommodationUnitService;
import net.dubrouski.fams.service.AddressService;

@Path("/accommodations")
@Produces("application/json")
@Consumes("application/json")
@RolesAllowed("contractAdmin")
public class AccommodationRest{
	
	@Inject
	AccommodationUnitService unitService;
	
	@Inject
	AddressService addressService;
	
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
	@Path("/{id}")
	public Response updateAccommodation(@PathParam("id") Long id, AccommodationUnit unit){
		try{
			AccommodationUnit u = unitService.getAccommodationById(id);			
			u.setDepositAmount(unit.getDepositAmount());
			u.setIsActive(unit.getIsActive());
			u.setName(unit.getName());
			unitService.update(u);
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
	@Path("/{id}")
	public Response deleteAccommodation(@PathParam("id") Long id){
		try{
			AccommodationUnit u = unitService.getAccommodationById(id); 
			unitService.delete(u);
			return Response.ok().entity(u).build();
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
	
	@PUT
	@Path("/{id}/set_price")
	public Response setPrice(@PathParam("id") Long id, Price price){
		try{
			AccommodationUnit unit = unitService.getAccommodationById(id);
			if(unit != null){
				unitService.setPrice(unit, price);
				return Response.ok().entity(unit).build();
			}
			else{
				return Response.status(400).entity("AccommodationUnit with id: " + id + " not found.").build();
			}			
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/{id}/set_address")
	public Response setAddress(@PathParam("id") Long id, @QueryParam("address_id") Long addressId){
		try{
			AccommodationUnit unit = unitService.getAccommodationById(id);
			Address a = addressService.getAddressById(addressId);
			if(unit != null){
				if(a != null){
					unitService.setAddressWithChildren(unit, a);
					return Response.ok().entity(unit).build();
				}
				else{
					return  Response.status(400).entity("Address with id: " + addressId + " not found.").build();
				}
			}
			else{
				return  Response.status(400).entity("AccommodationUnit with id: " + id + " not found.").build();
			}
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
}
