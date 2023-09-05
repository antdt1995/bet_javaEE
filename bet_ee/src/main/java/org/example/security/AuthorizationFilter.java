package org.example.security;

import org.example.enumclass.RoleEnum;
import org.example.exception.ErrorMessage;
import org.example.exception.ResponseBody;
import org.example.exception.SecurityException;

import javax.annotation.security.DenyAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthorizationFilter implements ContainerRequestFilter {


    @Context
    private ResourceInfo info;

    @Inject
    private AuthenticationService authenticationService;

    @Override
    public void filter(ContainerRequestContext request) {
        //@DenyAll in class or method: Abort Always
        if (isDenied()) {
            request.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseBody(Response.Status.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.FORBIDDEN_ACCESS))
                    .build());
            return;
        }

        RolesAllowed methodRoles = info.getResourceMethod().getAnnotation(RolesAllowed.class);
        RolesAllowed classRoles = info.getResourceClass().getAnnotation(RolesAllowed.class);

        if (methodRoles == null && classRoles == null) {

            return;
        }

        String authHeader = request.getHeaderString("Authorization");
        if (isNotValidJwt(authHeader)) {
            request.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new ResponseBody(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS)).build());
            return;
        }

        RoleEnum role = authenticationService.getRoleFromToken(authHeader);
        String email = authenticationService.getUsernameFromToken(authHeader);
        SecurityContext sc = new RequestSecurityContext(new UserPrincipal(email, role));


        request.setSecurityContext(sc);

        //Forbidden when class-level OR method-level roles do not match
        if (hasNoRole(classRoles, request.getSecurityContext())
                || hasNoRole(methodRoles, request.getSecurityContext())) {
            request.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ResponseBody(Response.Status.FORBIDDEN, ErrorMessage.KEY_FORBIDDEN_ACCESS, ErrorMessage.FORBIDDEN_ACCESS)).build());
        }
    }

    private boolean isDenied() {
        DenyAll methodDenyAll = info.getResourceMethod().getAnnotation(DenyAll.class);
        DenyAll classDenyAll = info.getResourceClass().getAnnotation(DenyAll.class);
        return methodDenyAll != null || classDenyAll != null;
    }

    private boolean isNotValidJwt(String header) {
        if (header == null) {
            return true;
        }

        try {
            authenticationService.validateJwtToken(header);
        } catch (SecurityException e) {
            return true;
        }

        return !header.startsWith("Bearer ");
    }

    private boolean hasNoRole(RolesAllowed anno, SecurityContext sc) {
        if (anno == null) {
            return false;
        }

        String[] roleStrings = anno.value();
        for (String roleString : roleStrings) {
            if (sc.isUserInRole(roleString)) {
                return false;
            }
        }
        return true;
    }
}
