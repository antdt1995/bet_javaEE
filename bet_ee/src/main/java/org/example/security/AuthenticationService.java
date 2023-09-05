package org.example.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.collections4.CollectionUtils;
import org.example.enumclass.RoleEnum;
import org.example.exception.EntityNotFoundException;
import org.example.exception.ErrorMessage;
import org.example.exception.SecurityException;
import org.example.account.AccountDTO;
import org.example.account.AccountService;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Stateless
public class AuthenticationService {
    public static final String SECRET_KEY = "betting";
    public static final String ISSUER = "ronaldo";
    private static final String USERNAME = "USERNAME";
    private static final String ROLE = "ROLE";
    private static final String BEARER = "Bearer";
    private static final Validator validator =
            Validation.byDefaultProvider()
                    .configure()
                    .messageInterpolator(new ParameterMessageInterpolator())
                    .buildValidatorFactory()
                    .getValidator();
    @Inject
    private AccountService accountService;

    public JwtResponse login(JwtRequest jwtRequest) throws EntityNotFoundException {
        String username = jwtRequest.getUsername().trim();
        verifyJwtRequest(jwtRequest);
        String token = generateToken(jwtRequest);
        AccountDTO accountDTO = accountService.findByUsername(username);
        RoleEnum roleEnum = accountDTO.getRoleEnum();
        return new JwtResponse(username, token, roleEnum);
    }

    public String generateToken(JwtRequest jwtRequest) throws EntityNotFoundException {
        if (!checkAuthentication(jwtRequest.getUsername(), jwtRequest.getPassword())) {
            throw new EntityNotFoundException(ErrorMessage.ACCOUNT_NOT_FOUND_MSG_KEY, ErrorMessage.ACCOUNT_NOT_FOUND_MSG);
        }
        Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
        return JWT.create().withClaim(USERNAME, jwtRequest.getUsername())
                .withIssuer(ISSUER)
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID().toString())
                .withClaim(USERNAME, jwtRequest.getUsername())
                .withClaim(ROLE, String.valueOf(accountService.findByUsername(jwtRequest.getUsername()).getRoleEnum()))
                .withExpiresAt(new Date(System.currentTimeMillis() + "600000"))
                .sign(algorithm);
    }

    public void validateJwtToken(String token) throws SecurityException {
        if (token == null) {
            throw new SecurityException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
        try {

            Algorithm algorithm = Algorithm.HMAC512(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER).build();
            verifier.verify(token.substring(BEARER.length()).trim());
        } catch (JWTVerificationException | IllegalArgumentException e) {
            throw new SecurityException(Response.Status.UNAUTHORIZED, ErrorMessage.KEY_UNAUTHORIZED_ACCESS, ErrorMessage.UNAUTHORIZED_ACCESS);
        }
    }

    private Boolean checkAuthentication(String username, String password) throws EntityNotFoundException {
        AccountDTO accountDTO = accountService.findByUsername(username);
        return BCrypt.checkpw(password, accountDTO.getPassword());
    }
    public RoleEnum getRoleFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return RoleEnum.valueOf(decodedJWT.getClaim(ROLE).asString());
    }

    public String getUsernameFromToken(String authorization) {
        String token = authorization.substring(BEARER.length()).trim();
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(USERNAME).asString();
    }


    public void verifyJwtRequest(JwtRequest jwtRequest) {
        Set<ConstraintViolation<JwtRequest>> violations = validator.validate(jwtRequest);

        if (CollectionUtils.isNotEmpty(violations)) {
            throw new ConstraintViolationException(violations);
        }

    }
}
