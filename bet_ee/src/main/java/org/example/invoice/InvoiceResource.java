package org.example.invoice;

import org.example.exception.EntityNotFoundException;
import org.example.exception.InputValidationException;
import org.example.invoicedetail.InvoiceDetailDTO;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Path("/invoices")
public class InvoiceResource {
    @Inject
    private InvoiceService invoiceService;

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(List<InvoiceDetailDTO> invoiceDetailDTOS) throws InputValidationException, EntityNotFoundException {
        InvoiceDTO invoice = invoiceService.create(invoiceDetailDTOS);
        return Response.ok(URI.create("/invoices/" + invoice.getId())).entity(invoice).build();
    }
}
