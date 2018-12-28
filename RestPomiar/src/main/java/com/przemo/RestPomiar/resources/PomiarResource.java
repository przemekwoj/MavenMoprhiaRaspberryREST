package com.przemo.RestPomiar.resources;

import java.util.List;

import javax.inject.Singleton;
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

import org.bson.types.ObjectId;

import com.przemo.RestPomiar.exception.DataNotFoundException;
import com.przemo.RestPomiar.resources.entity.Pomiar;
import com.przemo.RestPomiar.services.PomiarService;


//it will create this unit just once when the server starts

@Path("/pomiary")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PomiarResource
{
	private PomiarService pomiarService = new PomiarService();
		
	
	@GET
	public List<Pomiar> getAllPomiars()
	{
		return pomiarService.getAllPomiars();
	}
	
	@GET
	@Path("/{pomiarId}")
	public Pomiar getOnePomiar( @PathParam("pomiarId") ObjectId id)
	{
		return pomiarService.getOnePomiar(id);
	}
	
	@DELETE
	@Path("/{pomiarId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteOnePomiar( @PathParam("pomiarId") ObjectId id)
	{
		return pomiarService.deleteOnePomiar(id);
	}
	
	@GET
	@Path("/scope")
	public List<Pomiar> getScopePomiars(@QueryParam("from") int from, @QueryParam("to") int to) throws DataNotFoundException
	{
		return pomiarService.getScopePomiars(from,to);
	}
	
	@PUT
	@Path("/update")
	public Pomiar updateOnePomiar( @QueryParam("id") ObjectId id, @QueryParam("value") long value)
	{
		return pomiarService.updateOnePomiar(id,value);
	}
	
	@POST
	@Path("/addPomiar")
	public Pomiar addPomiar(Pomiar pomiar)
	{
		return pomiarService.addPomiar(pomiar);
				
	}
}
