package com.customer.util;

import java.time.Instant;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

public class JwtUtils {

	private JwtUtils() {}

	public static Instant extractExpiration(String jwt) {
		int i = jwt.lastIndexOf('.');
		String withoutSignature = jwt.substring(0, i + 1);
		Claims claims = Jwts.parserBuilder()
				.build()
				.parseClaimsJwt(withoutSignature)
				.getBody();

		return claims.getExpiration().toInstant();
	}
}
