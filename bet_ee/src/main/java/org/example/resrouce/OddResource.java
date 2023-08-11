package org.example.resrouce;

import org.example.entity.Odd;
import org.example.exception.EntityNotFoundException;
import org.example.model.OddDTO;
import org.example.service.OddService;

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
}
