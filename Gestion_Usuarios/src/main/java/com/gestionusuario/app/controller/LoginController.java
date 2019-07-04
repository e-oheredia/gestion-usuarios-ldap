package com.gestionusuario.app.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.gestionusuario.app.entity.Usuario;

import com.gestionusuario.app.security.Decoder;
import com.gestionusuario.app.security.JwtTokenUtil;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.SesionService;

import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {

	public int UsuarioActual;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoginUsuarioService loginusuarioservice;

	@Autowired
	private SesionService sesionservice;

	@Value("${ruta.intranet}")
	private String rutaIntranet;
	
	@Value("${ruta.port}")
	private int port;


	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> register(HttpServletRequest header,  HttpServletResponse response) throws AuthenticationException, Exception {

		String[] part;
		Decoder decoder = new Decoder();
		part = decoder.decode(header, AUTHENTICATION_PREFIX);
		

		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(part[0], part[1]));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		final Usuario usuario = loginusuarioservice.findOne(part[0]);
		if (usuario.getActivo() == 0) {
			response.setStatus(401);
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}
		UsuarioActual = sesionservice.CrearSesion(usuario.getIdUsuario());
		final String token = jwtTokenUtil.generateToken(usuario, UsuarioActual);
		final String rt = jwtTokenUtil.refreshToken(token);
		
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	
		params.add("token", token);
		params.add("rt", rt);
		
		
		UriComponents uriComponents = UriComponentsBuilder.newInstance()
	            .scheme("http")
	            .host(rutaIntranet)
	            .port(port)
	            .queryParams(params).build(true);
		
		URI location = uriComponents.toUri();
		
		Map<String, String> respuesta = new HashMap<>();
		
		respuesta.put("ruta", location.toString());
		
//		response.sendRedirect(location.toString());
//		response.setStatus(302);
		
		return new ResponseEntity<Map<String, String>>(respuesta, HttpStatus.OK);
	}

	@RequestMapping(value = "/cerrarsession", method = RequestMethod.POST)
	public ResponseEntity<?> cerrar(@RequestBody String idsesion) throws Exception {

		sesionservice.CerrarSesion(Integer.parseInt(idsesion));

		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
//	@RequestMapping(value = "/correo", method = RequestMethod.POST)
//	public ResponseEntity<?> RecuperarCorreo(@RequestBody String correo) throws Exception {
//
//		return new ResponseEntity<>(HttpStatus.OK);
//	}	

}
