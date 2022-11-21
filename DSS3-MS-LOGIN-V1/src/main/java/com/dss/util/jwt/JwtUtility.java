package com.dss.util.jwt;

import com.dss.util.exceptions.JwtTokenMalformedException;
import com.dss.util.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static com.dss.util.jwt.JwtProperties.*;

@Component
@Slf4j
public class JwtUtility {

    public Claims getClaims(String token){
        try {
            Claims body = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public void decodeJWT(String token) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        JSONObject jObject  = new JSONObject(payload); // json
        String roles = jObject.getString("roles"); // get the name from data.

        log.debug("JwtUtility | decodeJWT | header : {}", header);
        log.debug("JwtUtility | decodeJWT | payload : {}", payload);

        log.debug("JwtUtility | decodeJWT | roles : {}", roles);
    }

    public String getUserRole(String token) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jObject  = new JSONObject(payload);
        return jObject.getString("roles");
    }

    public boolean checkTokenExpiration(String token){
        Claims claim = Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
        if(claim.getExpiration().before(new Date())){
            log.error("JwtUtility | checkTokenExpiration | Expired JWT token");
            return true;
        }
        log.error("JwtUtility | checkTokenExpiration | OK");
        return false;
    }


    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }
}
