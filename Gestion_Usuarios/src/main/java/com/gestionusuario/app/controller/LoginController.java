package com.gestionusuario.app.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.gestionusuario.app.dao.IDominioDao;
import com.gestionusuario.app.dao.IPerfilDao;
import com.gestionusuario.app.dao.IUsuarioDao;
import com.gestionusuario.app.edao.EmpleadoEdao;
import com.gestionusuario.app.edao.GrupoRedEdao;
import com.gestionusuario.app.entity.Dominio;
import com.gestionusuario.app.entity.Perfil;
import com.gestionusuario.app.entity.Usuario;

import com.gestionusuario.app.security.Decoder;
import com.gestionusuario.app.security.JwtTokenUtil;
import com.gestionusuario.app.service.LoginUsuarioService;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.SesionService;

import static com.gestionusuario.app.enumerator.TipoAutenticacionEnum.ACTIVE_DIRECTORY;
import static com.gestionusuario.app.enumerator.TipoAutenticacionEnum.REGULAR;

import static com.gestionusuario.app.enumerator.Identificadores.AUTHENTICATION_PREFIX;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {

	public Long sesionActual;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private LoginUsuarioService loginusuarioservice;

	@Autowired
	private SesionService sesionservice;
	
	@Autowired
	private EmpleadoEdao empleadoEdao;

	@Value("${ruta.intranet}")
	private String rutaIntranet;
	
	@Value("${ruta.port}")
	private int port;
	
	@Autowired
	private IUsuarioDao usuarioDao;

	@Autowired
	private GrupoRedEdao grupoRedEdao;
	
	@Autowired
	private IPerfilDao perfilDao;
	
	@Autowired
	private IDominioDao dominioDao;
	
	@Autowired
	private PerfilService perfilservice;
	
	//@RequestMapping(value = "/token", method = RequestMethod.POST)
	@PostMapping("/token/{tipoAut}")
	@Transactional
	public ResponseEntity<?> register(HttpServletRequest header,  HttpServletResponse response,@RequestBody(required=false) Dominio dominio ,@PathVariable Long tipoAut) throws AuthenticationException, Exception {

		String[] part;
		Decoder decoder = new Decoder();
		part = decoder.decode(header, AUTHENTICATION_PREFIX);
		String token = "";
		String rt = "";
		
		if(dominio==null && tipoAut==REGULAR) {
			
			Map<String,Object> empleadoRegular = new HashMap<>();
			final Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(part[0], part[1]));
			SecurityContextHolder.getContext().setAuthentication(authentication);		
			final Usuario usuario = loginusuarioservice.findOne(part[0]);
			
			if (usuario.getActivo() == 0) {
				response.setStatus(401);
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			int perfilId = perfilservice.ObtenerPerfilID(usuario.getIdUsuario());
			String perfilNombre = perfilservice.ObtenerPerfil(usuario.getIdUsuario());
			empleadoRegular.put("idperfil", perfilId);
			empleadoRegular.put("perfil", perfilNombre);
			empleadoRegular.put("matricula", usuario.getMatricula());
			empleadoRegular.put("id", usuario.getIdUsuario());
			empleadoRegular.put("nombres", usuario.getNombre());
			sesionActual = sesionservice.CrearSesion(usuario.getMatricula(),tipoAut);		
			token = jwtTokenUtil.generateToken(empleadoRegular, sesionActual);
			rt = jwtTokenUtil.refreshToken(token);
			
		}else {
			
			Map<String,Object> empleadoAD = empleadoEdao.findEmpleadoByMatricula(part[0]);
			if(empleadoAD.isEmpty()) {
				response.setStatus(401);
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			
			Dominio dominioBD = dominioDao.findById(dominio.getDominioId()).orElse(null);
			
			if(dominioBD==null) {
				response.setStatus(401);
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			Map<String, Object> grupoRed = grupoRedEdao.listarGruposAD(dominioBD.getUrl(), dominioBD.getBaseDn(), "", "", "uid="+part[0], part[1]);
			if(grupoRed==null) {
					response.setStatus(401);
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			Perfil perfilEncontrado = new Perfil();
			String gruposred = grupoRed.get("grupos").toString();
			String grupolst1 = gruposred.replace("[", "");
			String grupolst2 = grupolst1.replace("]", "");
			String[] grupos = grupolst2.split(",");
			for(int i=0;i<grupos.length;i++) {
				perfilEncontrado = perfilDao.findPerfilByNombreGrupoRed(grupos[i]);
				if(perfilEncontrado==null) {
					response.setStatus(401);
					return new ResponseEntity<>(HttpStatus.FORBIDDEN);
				}else {
					break;
				}
			}
			empleadoAD.put("idperfil", perfilEncontrado.getIdPerfil());
			empleadoAD.put("perfil", perfilEncontrado.getNombre());
			sesionActual = sesionservice.CrearSesion(empleadoAD.get("matricula").toString(),tipoAut);
			token = jwtTokenUtil.generateToken(empleadoAD, sesionActual);
			rt = jwtTokenUtil.refreshToken(token);
		}
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
