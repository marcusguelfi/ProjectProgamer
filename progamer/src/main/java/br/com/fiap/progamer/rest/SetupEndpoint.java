package br.com.fiap.progamer.rest;

import java.util.List;

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

import br.com.fiap.dao.SetupDAO;
import br.com.fiap.model.Setup;

@Path("/setups")
@Produces(MediaType.APPLICATION_JSON)
public class SetupEndpoint {

	private SetupDAO dao = new SetupDAO();

	@GET
	public Response index() {
		try {
			List<Setup> list = dao.getAll();
			return Response.status(Response.Status.OK).entity(list).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Setup setup) {
		if (setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(setup);
			return Response.status(Response.Status.CREATED).entity(setup).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Setup setup = dao.findById(id);
		if (setup == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.status(200).entity(setup).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Setup setup) {
		if (id == null || setup == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (dao.findById(id) == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		setup.setId(id);
		try {
			dao.update(setup);
			return Response.status(Response.Status.OK).entity(setup).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id) {
		Setup toDelete = dao.findById(id);
		if (id == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		if (toDelete == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		try {
			dao.delete(toDelete);
			return Response.status(Response.Status.NO_CONTENT).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}


}
