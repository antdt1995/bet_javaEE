package org.example.resrouce;

import org.example.exception.EntityNotFoundException;
import org.example.model.AccountDTO;
import org.example.service.AccountService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/accounts")
public class AccountResource {
    @Inject
    private AccountService accountService;

    @GET
    @Path("/{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public AccountDTO findByUsername(@PathParam("username") String username) throws EntityNotFoundException {
        return accountService.findByUsername(username);
    }
}
