package blossom.reports_service.inbound.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import blossom.reports_service.model.Role;
import blossom.reports_service.model.Exceptions.InvalidException;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JwtValidator {

    private final Logger LOG = LoggerFactory.getLogger(getClass());
    private PublicKey publicKey;

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String bearerToken = httpServletRequest.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean isValidJWT(String jwt) {
        try {
            Jwts.parser()
                    .verifyWith(publicKey)
                    .build()
                    .parseSignedClaims(jwt);
            return true;
        } catch (Exception e) {
            LOG.info("Token rejected because " + e.getMessage());
            throw new InvalidException("JWT invalid");
        }
    }

    public Authentication getAuthentication(String jwt) {
        return new UsernamePasswordAuthenticationToken(getUserEmail(jwt), "", getRoles(jwt));
    }

    public String getUserEmail(String jwt) {
        final Jws<Claims> token = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(jwt);
        final Claims payload = token.getPayload();
        return payload.getSubject();
    }

    /**
     * Extracts the user roles from a JWT.
     *
     * @param jwt
     * @return
     */
    public Collection<GrantedAuthority> getRoles(String jwt) {
        Collection<GrantedAuthority> result = new ArrayList<>();
        final Jws<Claims> token = Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(jwt);
        final Claims payload = token.getPayload();
        final String authClaim = payload.get("auth", String.class);
        if (authClaim.equals("USER")) {
            result.add(Role.USER);
        } else if (authClaim.equals("ADMIN")) {
            result.add(Role.ADMIN);
        }
        return result;
    }
}
