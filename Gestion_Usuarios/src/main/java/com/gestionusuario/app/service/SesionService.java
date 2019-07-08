package com.gestionusuario.app.service;

public interface SesionService {
	public Long CrearSesion(String matricula, Long tipoAut) throws Exception;
	public int CerrarSesion(int idSesion) throws Exception;

}
