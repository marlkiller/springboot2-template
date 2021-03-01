package com.bespin.wzu3.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;

/**
 * JWT 工具类
 */
@Component
public class JwtTokenUtil implements Serializable {

    private static final String CLAIM_KEY_USERNAME = "sub";

    /**
     * 5天(毫秒)
     */
    private static final long EXPIRATION_TIME = 432000000;
    /**
     * JWT密码
     */
    private static final String SECRET = "secret";


    /**
     * 签发JWT
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>(16);
        claims.put( CLAIM_KEY_USERNAME, userDetails.getUsername() );

        return Jwts.builder()
                .setClaims( claims )
                .setExpiration( new Date( Instant.now().toEpochMilli() + EXPIRATION_TIME  ) )
                .signWith( SignatureAlgorithm.HS512, SECRET )
                .compact();
    }

    /**
     * 验证JWT
     */
    public Boolean validateToken(String token) {

        Claims claims = getClaimsFromToken(token);
        if (claims == null) {
            return false;
        }

        String username = claims.getSubject();
        if (StringUtils.isEmpty(username)) {
            return false;
        }

        // 添加此判断即要进行单点登录
        // 没有logout接口，不能做
        //if(!jwtToken.equals(token)){
        //    return false;
        //}

        return !isTokenExpired( token );


        // JwtUser user = (JwtUser) userDetails;
        // String username = getUsernameFromToken( token );
        // return (username.equals( user.getUsername() ) && !isTokenExpired( token ));
    }

    /**
     * 获取token是否过期
     */
    public Boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken( token );
        return expiration.before( new Date() );
    }

    /**
     * 根据token获取username
     */
    public String getUsernameFromToken(String token) {
        String username = getClaimsFromToken( token ).getSubject();
        return username;
    }

    /**
     * 获取token的过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = getClaimsFromToken( token ).getExpiration();
        return expiration;
    }

    /**
     * 解析JWT
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey( SECRET )
                .parseClaimsJws( token )
                .getBody();
        return claims;
    }


    public UserDetails getUserDetailsWithToken (String token) {
        Claims fromToken = getClaimsFromToken(token);
        String username = fromToken.get(CLAIM_KEY_USERNAME, String.class);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        return new JwtUser(username);
    }
}