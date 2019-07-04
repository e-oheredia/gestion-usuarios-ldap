package com.gestionusuario.app.service;




public interface PermisoService {
	
	public String ObtenerPermisosID(Long idUsuario) throws Exception;

	public String ObtenerPermisosNombre(int idPermiso) throws Exception;
}
