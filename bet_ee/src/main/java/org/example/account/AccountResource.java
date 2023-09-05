package org.example.account;

import org.example.exception.EntityNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/accounts")
public class AccountResource {
    @Inject
    private AccountService accountService;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Account findByUsername(@PathParam("username") String username) throws EntityNotFoundException {
        return accountService.findByUsername(username);
    }
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(AccountDTO accountDTO) {
        return Response.ok(accountService.create(accountDTO)).build();
    }
}
