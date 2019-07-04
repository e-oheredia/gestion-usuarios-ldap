package com.gestionusuario.app.repository;


public interface SesionDAO {

	public int CrearSesion(Long idUsuario) throws Exception;
	public int CerrarSesion(int idSesion) throws Exception;
	
}
