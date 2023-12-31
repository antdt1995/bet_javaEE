package org.example.odd;

import org.example.exception.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/matchs")
public class OddResource {
    @Inject
    private OddService oddService;

    @GET
    @Path("/odds")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(oddService.getAll()).build();
    }
    @GET
    @Path("/odds/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getById(@PathParam("id") Long id) throws EntityNotFoundException {
        return Response.ok(oddService.getById(id)).build();
    }

    @POST
    @Path("/{mathId}/odds")
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(OddDTO oddDTO, @PathParam("mathId")  Long matchId) throws EntityNotFoundException {
        OddDTO odd=oddService.create(oddDTO, matchId);
        return Response.ok(URI.create("/odds/" + oddDTO.getId())).entity(odd).build();
    }
    @GET
    @Path("/{matchId}/win")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getWinOdd(@PathParam("matchId") Long matchId){
        return Response.ok(oddService.findWinOdd(matchId)).build();
    }
}
