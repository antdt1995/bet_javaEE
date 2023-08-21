package org.example.security;

import lombok.*;
import org.example.exception.ErrorMessage;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtRequest {

    private String username;
    @NotBlank(message = ErrorMessage.PASSWORD_NULL_OR_BLANK)
    private String password;
}
