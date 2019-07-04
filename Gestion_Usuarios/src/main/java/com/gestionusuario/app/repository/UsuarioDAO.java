package com.gestionusuario.app.repository;

import java.util.List;

import com.gestionusuario.app.entity.Usuario;

public interface UsuarioDAO extends GenericDAO<Usuario> {

	public List<Usuario> ListarUsuarioPorNombre(String nombre) throws Exception;
	
	public boolean validarUsuario(Usuario usuario) throws Exception;
	
	public int ValidarUsuarioExistente(Long idUsuario) throws Exception;
	
	public int ValidarUsuarioActivo(Long idUsuario) throws Exception;
	
	public int ValidarDatosExistentes(String dni, String correo, String matricula) throws Exception;
	
	public int ValidarSiActivaDesactiva(Long idUsuarioDest, int activo) throws Exception;

	public boolean AsignarPerfilAUsuario(Long idUsuario, Long idPerfil) throws Exception;
	
	public boolean ModificarPerfilUsuario(Long idUsuario, Long idPerfil) throws Exception;
	
	public int LoguearUsuario(String username, String password) throws Exception;
	
	public int ValidarDatosExistentesAModificar(Long idUsuario, String dni, String correo, String matricula) throws Exception; 
	
}
