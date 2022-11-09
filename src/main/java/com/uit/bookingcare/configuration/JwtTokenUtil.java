package com.uit.bookingcare.configuration;

import com.uit.bookingcare.constant.MessageCode;
import com.uit.bookingcare.constant.enums.EUserType;
import com.uit.bookingcare.domain.user.User;
import com.uit.bookingcare.repository.user.UserRepository;
import com.uit.bookingcare.service.exception.BadRequestException;
import com.uit.bookingcare.utils.MessageHelper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

    final MessageHelper messageHelper;

    private static final long serialVersionUID = -2550185165626007488L;

    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private UserRepository userRepository;

    public JwtTokenUtil(MessageHelper messageHelper) {
        this.messageHelper = messageHelper;
    }

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            throw new BadRequestException(messageHelper.getMessage(MessageCode.Token.INVALID_TOKEN));
        }
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        User user = userRepository.findByEmail(userDetails.getUsername()).get();
        claims.put("role", user.getUserType());
        claims.put("email", user.getEmail());
        claims.put("full_name", user.getFirstName());
        if (user.getGender()!=null){
            claims.put("gender-vi", user.getGender().getValueVi());
            claims.put("gender-en", user.getGender().getValueEn());
        }
//        if (user.getUserType() == EUserType.DOCTOR) {
//
//        }
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
