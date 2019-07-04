package com.gestionusuario.app.repository;

public interface PermisoDAO {
	
	public String ObtenerPermisosID(Long idUsuario) throws Exception;
	
	public String ObtenerPermisosNombre(int idPermiso) throws Exception;

}
