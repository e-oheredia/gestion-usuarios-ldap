package com.gestionusuario.app.service;

public interface SesionService {
	public int CrearSesion(Long idUsuario) throws Exception;
	public int CerrarSesion(int idSesion) throws Exception;

}
