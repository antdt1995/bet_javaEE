package org.example.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.enumclass.RoleEnum;

import java.security.Principal;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserPrincipal implements Principal {
    private String email;
    private RoleEnum role;

    @Override
    public String getName() {
        return email;
    }
}
