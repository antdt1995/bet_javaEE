package org.example.security;

import org.example.exception.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/auth")
public class AuthenticationResource {
    @Inject
    private AuthenticationService authenticationService;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(JwtRequest jwtRequest) throws EntityNotFoundException {
        JwtResponse jwtResponse=authenticationService.login(jwtRequest);
        return Response.ok(jwtResponse).build();
    }
}
