package com.dss.gateway.util.jwt;

import com.dss.gateway.util.exceptions.JwtTokenMalformedException;
import com.dss.gateway.util.exceptions.JwtTokenMissingException;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

import static com.dss.gateway.util.jwt.JwtProperties.*;


@Component
@Slf4j
public class JwtUtility {

    public Claims getAllClaimsFromToken(final String token) {
        return Jwts.parser().setSigningKey(JWT_SECRET.getBytes()).parseClaimsJws(token).getBody();
    }

    public void checkTokenExpiration(String token){
        Claims claim = this.getAllClaimsFromToken(token);
        if(claim.getExpiration().before(new Date())){
            log.error("JwtUtility | checkTokenExpiration | Expired JWT token");
            throw new JwtTokenMalformedException("Expired JWT token");
        }
        log.debug("JwtUtility | checkTokenExpiration | OK");
    }

    public String getResources(String token) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jObject  = new JSONObject(payload);
        return jObject.getString("resources").replaceAll("[\\[\\]\"]", "");
    }

    public String getAction(String token) throws JSONException {
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String[] chunks = token.split("\\.");
        String payload = new String(decoder.decode(chunks[1]));
        JSONObject jObject  = new JSONObject(payload);
        return jObject.getString("actions").replaceAll("[\\[\\]\"]", "");
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
