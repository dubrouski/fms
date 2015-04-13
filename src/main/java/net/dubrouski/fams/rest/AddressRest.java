package net.dubrouski.fams.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import net.dubrouski.fams.model.Address;
import net.dubrouski.fams.service.AddressService;

@Path("/addresses")
@Produces("application/json")
@Consumes("application/json")
public class AddressRest {
	
	@Inject
	AddressService addressService;
	
	@GET
	public Response listAdresses(){
		try{
			List<Address> found = addressService.listAddresses();
			return Response.ok().entity(found).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@GET
	@Path("/{id}")
	public Response getById(@PathParam("id") Long id){
		try{
			Address a = addressService.getAddressById(id);
			return Response.ok().entity(a).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@POST
	public Response save(Address address){
		try{
			addressService.saveAddress(address);
			return Response.ok().entity(address).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") Long id){
		try{
			Address a = addressService.getAddressById(id);
			addressService.deleteAddress(a);
			return Response.ok().entity(a).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
	
	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") Long id, Address address){
		try{
			Address a = addressService.getAddressById(id);
			a.setCity(address.getCity());
			a.setFlatNumber(address.getFlatNumber());
			a.setLatitude(address.getLatitude());
			a.setLongitude(address.getLongitude());
			a.setStreetName(address.getStreetName());
			a.setStreetNumber(address.getStreetNumber());
			addressService.updateAddress(a);
			return Response.ok().entity(a).build();
		}
		catch(Exception ex){
			return Response.status(400).entity(ex.getMessage()).build();
		}
	}
}
