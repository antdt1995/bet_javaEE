package org.example.invoice;

import org.example.invoicedetail.InvoiceDetailMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi",uses = {InvoiceDetailMapper.class})
public interface InvoiceMapper {
    @Mapping(source = "account.username", target = "username")
    InvoiceDTO toDto(Invoice invoice);

    List<InvoiceDTO> toDtos(List<Invoice> invoices);
}
