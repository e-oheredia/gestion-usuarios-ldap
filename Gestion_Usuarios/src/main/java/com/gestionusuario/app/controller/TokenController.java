package com.gestionusuario.app.controller;

import static com.gestionusuario.app.enumerator.Identificadores.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.gestionusuario.app.enumerator.Identificadores.SIGNING_KEY;
import static com.gestionusuario.app.enumerator.Identificadores.TOKEN_PREFIX;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestionusuario.app.security.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/refreshtoken")
public class TokenController {
	
	@Autowired
	JwtTokenUtil jwtTokenUtil;
	
	@GetMapping
	public ResponseEntity<Map<String, Object>> getNewTokens(@RequestHeader("Authorization") String header) throws Exception{
		Map<String, Object> respuesta = new HashMap<String, Object>();
		String authtoken = header.replace(TOKEN_PREFIX, "");
		try {
			final Claims claims = jwtTokenUtil.getAllClaimsFromToken(authtoken);	
			
			String token = Jwts.builder()
					.setClaims(claims)
					.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
	                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY.getBytes())
	                .compact();
			
			String refreshTokenActual = jwtTokenUtil.refreshToken(token);
			respuesta.put("token", token);
			respuesta.put("refreshToken", refreshTokenActual);
			return new ResponseEntity<Map<String,Object>>(respuesta, HttpStatus.OK);
			
		}catch (ExpiredJwtException eje) {	
			return ResponseEntity.status(894).body(null);
		}catch (MalformedJwtException mje) {
			return ResponseEntity.status(498).body(null);
		}catch (SignatureException se) {
			return ResponseEntity.status(498).body(null);
		}
	}
	
	
}
