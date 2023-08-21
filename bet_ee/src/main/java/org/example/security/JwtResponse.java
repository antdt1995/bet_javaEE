package org.example.security;

import lombok.*;
import org.example.entity.RoleEnum;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class JwtResponse {
    private String token;
    private String email;

    private RoleEnum role;

    private String type = "Bearer";

    public JwtResponse(String token, String email, RoleEnum role) {
        this.token = token;
        this.email = email;
        this.role = role;

    }
}
