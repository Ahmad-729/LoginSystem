package login.LoginSystem.jwt;

import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;
import io.jsonwebtoken.*;
import login.LoginSystem.services.UserDetailsImpl;
import lombok.Value;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
public class JwtUtils {
    private static final Logger logger= LoggerFactory.getLogger(JwtUtils.class);

    @Value("mysecret")()
private String JwtSecret;

    @Value("1800000")
private int JwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal=(UserDetailsImpl) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+ JwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512 , JwtSecret)
                .compact();
    }
    public String getUserNameFromJwtToken(String token){
        return Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(token).getBody().getSubject();
    }
    public boolean validateJwtToken(String AuthToken){
        try {
            Jwts.parser().setSigningKey(JwtSecret).parseClaimsJws(AuthToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
return false;
    }
}