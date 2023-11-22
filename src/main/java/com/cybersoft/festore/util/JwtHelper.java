package com.cybersoft.festore.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${custom.token.key}")
    private String seckey;

    private long expiredTIme = 8 * 60 * 60 * 1000;

    public String generateToken(String data){
        //Lấy key đã lưu trữ và sử dụng để tạo ra token
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(seckey));
        //Sinh ra thời gian hết hạn mới
        Date date = new Date();
        long newDateMilis = date.getTime()+expiredTIme;
        Date newExpiredDate = new Date(newDateMilis);
        String token = Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .setExpiration(newExpiredDate)
                .compact();
        return token;
    }

    // Hàm giải mã token
    public String parseToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(seckey));
        String data = Jwts.parserBuilder()
                .setSigningKey(key).build() // Truyền key cần giảm mã
                .parseClaimsJws(token)
                .getBody().getSubject();// Lấy nội dung lưu trữ trong token
        return data;
    }

}
