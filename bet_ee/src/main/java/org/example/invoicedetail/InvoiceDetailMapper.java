package org.example.invoicedetail;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface InvoiceDetailMapper {
    @Mapping(source = "odd.id", target = "oddId")
    InvoiceDetailDTO toDTO(InvoiceDetail invoiceDetail);
    List<InvoiceDetailDTO> toDTOs(List<InvoiceDetail> invoiceDetails);
}
