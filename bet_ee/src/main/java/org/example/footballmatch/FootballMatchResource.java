package org.example.footballmatch;

import org.example.exception.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/football-matchs")
public class FootballMatchResource {
    @Inject
    private FootballMatchService footballMatchService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        try {
            return Response.ok(footballMatchService.getAll()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).entity("Contact admin").build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws EntityNotFoundException {
            return Response.ok(footballMatchService.getById(id)).build();
    }

    @POST
    @Path("/create-match/home/{homeId}/away/{awayId}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(FootballMatchDTO footballMatchDTO, @PathParam("homeId") Long homeId, @PathParam("awayId") Long awayId) throws EntityNotFoundException {
            FootballMatchDTO footballMatch = footballMatchService.create(footballMatchDTO, homeId, awayId);
            return Response.ok(URI.create("/football-matchs/" + footballMatchDTO.getId())).entity(footballMatch).build();

    }

    @PUT
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(FootballMatchDTO footballMatchDTO, @PathParam("id") Long id) throws EntityNotFoundException {

            footballMatchService.update(footballMatchDTO, id);
            return Response.ok(footballMatchService.update(footballMatchDTO, id)).build();

    }
    @DELETE
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response delete(@PathParam("id") Long id) throws EntityNotFoundException {
            footballMatchService.delete(id);
            return Response.noContent().build();

    }
}
