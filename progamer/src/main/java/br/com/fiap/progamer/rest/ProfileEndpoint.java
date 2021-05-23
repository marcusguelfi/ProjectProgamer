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

import br.com.fiap.dao.ProfileDAO;
import br.com.fiap.model.Profile;

@Path("/profiles")
@Produces(MediaType.APPLICATION_JSON)
public class ProfileEndpoint {

	private ProfileDAO dao = new ProfileDAO();

	@GET
	public Response index() {
		try {
			List<Profile> list = dao.getAll();
			return Response.status(Response.Status.OK).entity(list).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Profile profile) {
		if (profile == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		try {
			dao.save(profile);
			return Response.status(Response.Status.CREATED).entity(profile).build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

	}

	@GET
	@Path("{id}")
	public Response show(@PathParam("id") Long id) {
		Profile profile = dao.findById(id);
		if (profile == null) {
			return Response.status(404).build();
		}
		return Response.status(200).entity(profile).build();
	}

	@PUT
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Profile profile) {
		if (id == null || profile == null) {
			Response.status(400).build();
		}
		if (dao.findById(id) == null) {
			Response.status(404).build();
		}
		profile.setId(id);
		try {
			dao.update(profile);
			return Response.status(200).entity(profile).build();
		} catch (Exception e) {
			return Response.status(500).build();
		}

	}
	
	@DELETE
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id) {
		Profile toDelete = dao.findById(id);
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
