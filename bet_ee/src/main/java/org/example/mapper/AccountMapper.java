package org.example.mapper;

import org.example.entity.Account;
import org.example.model.AccountDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "cdi")

public interface AccountMapper {
    @Mapping(source = "customer.lastName", target = "lastName")
    @Mapping(source = "customer.firstName", target = "firstName")
    @Mapping(source = "customer.phone", target = "phone")
    @Mapping(source = "customer.bankAccount", target = "bankAccount")
    @Mapping(source = "customer.bankName", target = "bankName")

    AccountDTO toDTO (Account account);
    List<AccountDTO> toDTOList (List<Account> accountList);
}
