package net.javaguides.springboot.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import net.javaguides.springboot.model.User;

@Component
public class JwtUtil {

	private String secretKey = "mysecretkeymysecretkeymysecretkeymysecretkey";
	private Key key;

	@PostConstruct
	public void init() {
		key = Keys.hmacShaKeyFor(secretKey.getBytes());
	}

	public String createToken(User user) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("role", user.getRole().name());

		return Jwts.builder().setClaims(claims).setSubject(user.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30 * 24)) // 30 minutes
				.signWith(key, SignatureAlgorithm.HS256).compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody()
				.getExpiration();
		return expiration.before(new Date());
	}

}
