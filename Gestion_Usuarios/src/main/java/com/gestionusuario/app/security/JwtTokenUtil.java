package com.gestionusuario.app.security;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionusuario.app.edao.GrupoRedEdao;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.PermisoService;

import static com.gestionusuario.app.enumerator.Identificadores.ACCESS_TOKEN_VALIDITY_SECONDS;
import static com.gestionusuario.app.enumerator.Identificadores.REFRESH_TOKEN_VALIDITY_SECONDS;

import static com.gestionusuario.app.enumerator.Identificadores.SIGNING_KEY;




@Component
public class JwtTokenUtil implements Serializable{
	
	
	@Autowired
    private PermisoService permisoservice;
	
	@Autowired
	private PerfilService perfilservice;
	
	
	
	public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public  <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    
    public String refreshToken(String token) throws Exception {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return tokenrefresh(claims);
        } catch (Exception e) {
            final String errorMsg = "NO SE CREO EL REFRESH TOKEN";
            
            throw new Exception(errorMsg, e);
        }
    }
    
    // Obtiene los claims del token enviado
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //llama a generar Token
    public String generateToken(Map<String,Object> empleado, Long idsession) throws Exception {
        return tokenacceso(empleado,idsession);
    }
    
    //crea el token refresh
    private String tokenrefresh(Map<String, Object> claims) {
    		return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_VALIDITY_SECONDS*1000))                
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY.getBytes())
                .compact();
    }
    
    
    // Crea el token de acceso en el login 
    private String tokenacceso(Map<String,Object> empleado, Long idsession) throws Exception {
    	Long perfilId = Long.valueOf(empleado.get("idperfil").toString());
    	String permisosID = perfilservice.findPermisosPorPerfilId(perfilId);
    	List<Map<String, Object>> permisosNombre = new ArrayList<Map<String, Object>>();
        String[] permisosIDarray = permisosID.split(",");
        int[] intpermisosID = new int[permisosIDarray.length];
        for (int i = 0; i < permisosIDarray.length; i++) {
           String numberAsString = permisosIDarray[i];
           intpermisosID[i] = Integer.parseInt(numberAsString);
        }
        for (int i = 0; i < intpermisosID.length; i++) {
        	Map<String, Object> nombrePermiso = new HashMap<String, Object>();
        	nombrePermiso.put("id", intpermisosID[i]);
        	nombrePermiso.put("nombre", 
        			permisoservice.ObtenerPermisosNombre(intpermisosID[i]));
        	permisosNombre.add(nombrePermiso);
        }
        String jsonPermisos = new ObjectMapper().writeValueAsString(permisosNombre);
        Claims claims = Jwts.claims().setSubject(String.valueOf(empleado.get("id")));
        claims.put("idperfil", empleado.get("idperfil")); //CAMBIA
        claims.put("perfil", empleado.get("perfil").toString()); //CAMBIA
        claims.put("permisos", jsonPermisos);
        claims.put("matricula", empleado.get("matricula").toString());// REEMPLAZAR CON EL FRONT usuario.getMatricula x matricula 
        claims.put("idUsuario", empleado.get("id").toString());
        claims.put("usuario", empleado.get("nombres"));
        claims.put("idSesion", idsession);
        
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY.getBytes())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
              username.equals(userDetails.getUsername())
                    && !isTokenExpired(token));
    }

}
