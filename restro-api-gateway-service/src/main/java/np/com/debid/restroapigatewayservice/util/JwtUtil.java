package np.com.debid.restroapigatewayservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.function.Function;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Value("${restro.jwt.secret-key}")
    private String jwtSecret;


    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public int extractUserId(String token) {
        final Claims claims = extractAllClaims(token);
        return claims.get("userId", Integer.class);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(token).getPayload();
    }

    public void validateJwtToken(String authToken) {
        try {
            Jwts.parser().verifyWith(getSignInKey()).build().parseSignedClaims(authToken);
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
            throw new SignatureException("Error: Invalid JWT signature.", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
            throw new UnsupportedJwtException("Error: JWT token is unsupported.");
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "Error: JWT token is expired.");
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
            throw new UnsupportedJwtException("Error: JWT token is unsupported.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
            throw new IllegalArgumentException("Error: JWT claims string is empty.");
        }
    }

    private SecretKey getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}