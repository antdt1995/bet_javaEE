package org.example.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class RequestSecurityContext implements SecurityContext {

    private final UserPrincipal userPrincipal;

    public RequestSecurityContext(UserPrincipal userPrincipal) {
        this.userPrincipal = userPrincipal;
    }

    @Override
    public Principal getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public boolean isUserInRole(String s) {
        return userPrincipal.getRole().name().equals(s);
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return SecurityContext.BASIC_AUTH;
    }
}
