package com.trackAgile.security;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.trackAgile.Entity.User;
import com.trackAgile.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtService {
	
	@Autowired
	private UserRepository userRepository;
	
public static final String SECRET = "357638792F423F4428472B4B6250655368566D597133743677397A2443264629566787";
	
	public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }



//    public String GenerateToken(String username){
//        Map<String, Object> claims = new HashMap<>();
//        return createToken(claims, username);
//    } 

	
	public String generateToken(UserDetails userDetails) {
		
		String username = userDetails.getUsername();
		Map<String, Object> claims=new HashMap<>();
		List<String> userRoles = Arrays.asList("ROLE_USER","ROLE_ADMIN","ROLE_FE","ROLE_FRT","ROLE_MANAGER","ROLE_PACKAGE_LEAD","ROLE_PACKAGE_MANAGER");
		boolean isFirstLogin = false;
		
//		Optional<User> userOpt = Optional.empty();
//
//	    if (isEmail(username)) {
//	        // Find user by email
//	        userOpt = userRepository.findByEmail(username);
//	    } else {
//	        // Find user by username
//	        userOpt = userRepository.findByUserName(username);
//	    }

		
		Optional<User> userOpt = userRepository.findByUserName(username);
	    if (userOpt.isPresent()) {
	        Boolean isFirstLoginValue = userOpt.get().getIsFirstLogIn();
	        if (isFirstLoginValue != null) {
	            isFirstLogin = isFirstLoginValue.booleanValue();
	        }
	    }
		
		return createToken(claims,userDetails,userRoles,isFirstLogin);
//		return createToken(claims,userDetails,userRoles)
	}

//	private boolean isEmail(String username) {
//        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
//        Pattern pattern = Pattern.compile(emailRegex);
//        Matcher matcher = pattern.matcher(username);
//        return matcher.matches();
//    }


	private String createToken(Map<String, Object> claims, UserDetails userDetails,List<String> userRoles,boolean isFirstLogin) {
		 List<String> roles = userDetails.getAuthorities().stream()
               .map(GrantedAuthority::getAuthority)
               .collect(Collectors.toList());
		
		// Generate token using the claims
		return Jwts.builder()
				.setClaims(claims) 
				.setSubject(userDetails.getUsername())
				.claim("roles", roles)
				.claim("isFirstLogin", isFirstLogin)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 60))
				.signWith(getSignKey(),SignatureAlgorithm.HS256).compact();
	}
    
    
//    public static String generateToken(UserDetails userDetails) {
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return Jwts.builder()
//                .setSubject(userDetails.getUsername())
//                .claim("roles", roles)
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
//                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
////                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes(StandardCharsets.UTF_8))
//                .compact();
//    }

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}

}
