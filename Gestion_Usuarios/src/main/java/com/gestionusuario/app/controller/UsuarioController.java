package com.gestionusuario.app.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestionusuario.app.configuration.Conectar;
import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.enumerator.PermisosLista;
import com.gestionusuario.app.service.PerfilService;
import com.gestionusuario.app.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

	private static final String CONEXIONBD = "{\"RPTA\":\"NO SE PUDO ESTABLECER CONEXIÓN CON LA BASE DE DATOS\"}";
	private static final String PERFILEXISTE = "{\"RPTA\":\"EL PERFIL NO EXISTE O ESTA DESACTIVADO \"}";
	private static final String PERFILDESEXISTE = "{\"RPTA\":\"EL PERFIL QUE DESEA ASIGNAR, NO EXISTE \"}";
	private static final String PERMISO = "{\"RPTA\":\"EL USUARIO NO TIENE EL PERMISO PARA REALIZAR ESTA ACCIÓN\"}";
	private static final String USUARIOESTADO = "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION ESTA DESACTIVADO\"}";
	private static final String USUARIODESESTADO = "{\"RPTA\":\"EL USUARIO AL QUE INTENTA MODIFICAR ESTA DESACTIVADO\"}";
	private static final String USUARIOEXISTE = "{\"RPTA\":\"EL USUARIO QUE INTENTA REALIZAR LA CREACION NO EXISTE\"}";
	private static final String USUARIODESEXISTE = "{\"RPTA\":\"EL USUARIO AL QUE INTENTA MODIFICAR NO EXISTE\"}";

	@Autowired
	private Conectar conectar;

	@Autowired
	private UsuarioService usuarioservice;

	@Autowired
	private PerfilService perfilservice;

	public PerfilService getPerfilservice() {
		return perfilservice;
	}

	public void setPerfilservice(PerfilService perfilservice) {
		this.perfilservice = perfilservice;
	}

	public UsuarioService getUsuarioservice() {
		return usuarioservice;
	}

	public void setUsuarioservice(UsuarioService usuarioservice) {
		this.usuarioservice = usuarioservice;
	}

	@RequestMapping(value = "/CrearUsuario", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public @ResponseBody String CrearUsuario(@RequestBody String request) throws Exception {

		int existe = 0;
		int uactivo = 0;
		int aut = 0;
		int valor = 0;
		int perf = 0;
		int pactivo = 0;
		Integer idusr = 0;

		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		Usuario usuario = mapper.readValue(requestJson.getString("Usuario"), Usuario.class);
		String idPerfil = requestJson.getString("idPerfil");

		if (!conectar.validarcnx()) {
			return CONEXIONBD;
		}

		existe = this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));

		if (existe == 1) {
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if (uactivo == 1) {
				aut = this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario), PermisosLista.CreadorUsuarios);
				if (aut == 1) {
					if ((usuario.getNombre() != "") & (usuario.getApellido() != "") & (usuario.getMatricula() != "")
							& (usuario.getDni() != "") & (usuario.getCorreo() != "")) {
						valor = this.getUsuarioservice().ValidarDatosExistentes(usuario.getDni(), usuario.getCorreo(),
								usuario.getMatricula());
						switch (valor) {
						case 1:
							return "{\"RPTA\":\"DNI REPETIDO\"}";
						case 2:
							return "{\"RPTA\":\"MATRICULA REPETIDA\"}";
						case 3:
							return "{\"RPTA\":\"CORREO REPETIDO\"}";
						default:
							perf = this.getPerfilservice().ValidarPerfilExistente(Long.parseLong(idPerfil));
							if (perf == 1) {
								pactivo = this.getPerfilservice().ValidarPerfilActivo(Long.parseLong(idPerfil));
								if (pactivo == 1) {

									idusr = this.getUsuarioservice().insertar(usuario);
									this.getUsuarioservice().AsignarPerfilAUsuario(Long.valueOf(idusr.longValue()),
											Long.parseLong(idPerfil));

									return "{\"RPTA\":\"SE INSERTO EL USUARIO\"}";
								} else {
									return "{\"RPTA\":\"EL PERFIL NO ESTÁ ACTIVO\"}";
								}
							} else {
								return PERFILEXISTE;
							}
						}

					} else {
						return "{\"RPTA\":\"POR FAVOR, REGISTRE TODOS LOS DATOS DEL USUARIO\"}";
					}
				} else {
					return PERMISO;
				}
			} else {
				return USUARIOESTADO;
			}
		}

		return USUARIOEXISTE;
	}

	@RequestMapping(value = "/ModificarUsuario", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
	public @ResponseBody String ModificarUsuario(@RequestBody String request) throws Exception {

		int existe = 0;
		int uactivo = 0;
		int aut = 0;

		int existedes = 0;
		int uactivodes = 0;

		int valor = 0;
		int perf = 0;
		int pactivo = 0;

		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		Usuario usuarioDest = mapper.readValue(requestJson.getString("Usuario"), Usuario.class);
		String idPerfil = requestJson.getString("idPerfil");

		if (!conectar.validarcnx()) {
			return CONEXIONBD;
		}

		existe = this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));

		if (existe == 1) {
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));
			if (uactivo == 1) {
				aut = this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),
						PermisosLista.ModificadorUsuarios);
				if (aut == 1) {

					existedes = this.getUsuarioservice().ValidarUsuarioExistente(usuarioDest.getIdUsuario()); // destino
					if (existedes == 1) {
						uactivodes = this.getUsuarioservice().ValidarUsuarioActivo(usuarioDest.getIdUsuario()); // destino
						if (uactivodes == 1) {

							if ((usuarioDest.getNombre() != "") & (usuarioDest.getApellido() != "")
									& (usuarioDest.getDni() != "") & (usuarioDest.getMatricula() != "")
									& (usuarioDest.getCorreo() != "")) {
								valor = this.getUsuarioservice().ValidarDatosExistentesAModificar(
										usuarioDest.getIdUsuario(), usuarioDest.getDni(), usuarioDest.getCorreo(),
										usuarioDest.getMatricula());
								switch (valor) {
								case 1:
									return "{\"RPTA\":\"EL DNI INGRESADO SE REPITE EN LA BASE DE DATOS\"}";
								case 2:
									return "{\"RPTA\":\"LA MATRICULA INGRESADA SE REPITE EN LA BASE DE DATOS\"}";
								case 3:
									return "{\"RPTA\":\"EL CORREO INGRESADO SE REPITE EN LA BASE DE DATOS\"}";
								default:

									if (idPerfil.length() < 1) {

										this.getUsuarioservice().modificar(usuarioDest);
										return "{\"RPTA\":\"SE HA ACTUALIZADO EL USUARIO CORRECTAMENTE (S/P)\"}";

									} else {

										perf = this.getPerfilservice().ValidarPerfilExistente(Long.parseLong(idPerfil)); // destino
										if (perf == 1) {
											pactivo = this.getPerfilservice()
													.ValidarPerfilActivo(Long.parseLong(idPerfil)); // destino
											if (pactivo == 1) {
												this.getUsuarioservice().modificar(usuarioDest);
												this.getUsuarioservice().ModificarPerfilUsuario(
														usuarioDest.getIdUsuario(), Long.parseLong(idPerfil));

												return "{\"RPTA\":\"SE HA ACTUALIZADO EL USUARIO CORRECTAMENTE\"}";
											} else {

												return "{\"RPTA\":\"EL PERFIL ASIGNADO NO ESTÁ ACTIVO\"}";
											}
										} else {
											return PERFILDESEXISTE;
										}
									}
								}

							} else {
								return "{\"RPTA\":\"POR FAVOR, REGISTRE TODOS LOS DATOS DEL USUARIO\"}";
							}
						} else {
							return "{\"RPTA\":\"NO PUEDE ACTUALIZAR LA INFORMACIÓN DE UN USUARIO DESACTIVADO\"}";
						}
					} else {
						return USUARIODESEXISTE;
					}
				} else {
					return PERMISO;
				}
			} else {
				return USUARIOESTADO;
			}
		}

		return USUARIOEXISTE;
	}

	@RequestMapping(value = "/ModificarEstadoUsuario", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.PATCH)
	public @ResponseBody String ModificarEstadoUsuario(@RequestBody String request) throws Exception {

		int existe = 0;
		int uactivo = 0;
		int permiso = 0;
		int estado = 0;
		int udest = 0;

		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String idUsuario = requestJson.getString("idUsuario");
		String idUsuarioDest = requestJson.getString("idUsuarioDest");
		int activo = requestJson.getInt("activo");

		if (!conectar.validarcnx()) {
			return CONEXIONBD;
		}

		existe = this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuario));

		if (existe == 1) {
			uactivo = this.getUsuarioservice().ValidarUsuarioActivo(Long.parseLong(idUsuario));

			if (uactivo == 1) {
				permiso = this.getPerfilservice().ValidarPermisos(Long.parseLong(idUsuario),
						PermisosLista.ModificadorEstadoUsuarios);

				if (permiso == 1) {
					udest = this.getUsuarioservice().ValidarUsuarioExistente(Long.parseLong(idUsuarioDest));

					if (udest == 1) {
						estado = this.getUsuarioservice().ValidarSiActivaDesactiva(Long.parseLong(idUsuarioDest),
								activo);

						switch (estado) {
						case 1:
							return "{\"RPTA\":\"EL USUARIO YA SE ENCUENTRA ACTIVADO\"}";
						case 2:
							return "{\"RPTA\":\"EL USUARIO YA SE ENCUENTRA DESACTIVADO\"}";
						case 3:
							return "{\"RPTA\":\"LA ACTIVACIÓN DEL USUARIO SE LOGRÓ CON ÉXITO\"}";
						default:
							return "{\"RPTA\":\"SE REALIZÓ LA DESACTIVACIÓN DEL USUARIO\"}";
						}
					} else {
						return "{\"RPTA\":\"EL USUARIO AL QUE SE DESEA MODIFICAR NO EXISTE\"}";
					}
				} else {
					return PERMISO;
				}
			} else {
				return USUARIOESTADO;
			}
		}
		return USUARIOEXISTE;
	}

	@RequestMapping(value = "/Loguear", consumes = "application/json; charset=utf-8", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String LoguearUsuario(@RequestBody String request) throws Exception {

		int log = 0;
		int uactivo = 0;
		int permiso = 0;
		int estado = 0;
		int udest = 0;

		ObjectMapper mapper = new ObjectMapper();
		JSONObject requestJson = new JSONObject(request);
		String username = requestJson.getString("username");
		String password = requestJson.getString("password");

		if (!conectar.validarcnx()) {
			return CONEXIONBD;
		}

		log = this.getUsuarioservice().LoguearUsuario(username, password);

		if (log == 1) {
			return "{\"RPTA\":\"USUARIO LOGUEADO\"}";
		}
		return "{\"RPTA\":\"EL USUARIO NO EXISTE EN LA BD\"}";
	}

	@GetMapping("/{id}/nombre")
	public ResponseEntity<String> obtenerNombreUsuario(@PathVariable Long id) throws Exception {
		return new ResponseEntity<String>(usuarioservice.findNombreUsuario(id), HttpStatus.OK);
	}

}
