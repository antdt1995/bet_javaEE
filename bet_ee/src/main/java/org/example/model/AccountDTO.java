package org.example.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.example.entity.RoleEnum;
import org.example.exception.ErrorMessage;

import javax.validation.constraints.Min;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private Long id;
    @Min(value = 3L, message = ErrorMessage.USERNAME_INVALID)
    private String username;
    @JsonIgnore
    private String password;
    private Double totalBalance;
    private String email;
    private String lastName;
    private String firstName;
    private String phone;
    private String bankName;
    private String bankAccount;
    private RoleEnum roleEnum;
}
