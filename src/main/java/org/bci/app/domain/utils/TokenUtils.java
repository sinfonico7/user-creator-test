package org.bci.app.domain.utils;

import org.bci.app.domain.entities.User;
import org.springframework.core.env.Environment;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenUtils {

    private static Environment environment;

    public TokenUtils (Environment environment){
        this.environment = environment;
    }

    public static String generateTokenFromUser(User user, String uuid){
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("uuid",user.getId());
        userMap.put("email",user.getEmail());
        userMap.put("name",user.getName());
        String jwtSecret = environment.getProperty("jwt.secret");
        if(jwtSecret.isEmpty()) jwtSecret = generate512valueToken();
        byte[] secret = jwtSecret.getBytes(StandardCharsets.UTF_8);
        Key key = Keys.hmacShaKeyFor(secret);
        long tokenValidity = Long.parseLong(environment.getProperty("jwt.validity"));
        return Jwts.builder().setSubject(uuid).setClaims(userMap).setIssuedAt(new Date(System.currentTimeMillis())).setExpiration(new Date(System.currentTimeMillis() + tokenValidity * 1000L)).signWith(key, SignatureAlgorithm.HS512).compact();
    }

    private static String generate512valueToken(){
        Random random = ThreadLocalRandom.current();
        byte[] r = new byte[512];
        random.nextBytes(r);
        return new String(r);
    }
}
