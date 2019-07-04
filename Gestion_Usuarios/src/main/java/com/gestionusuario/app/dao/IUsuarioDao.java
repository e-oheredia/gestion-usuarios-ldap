package com.gestionusuario.app.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.gestionusuario.app.entity.Usuario;

public interface IUsuarioDao extends CrudRepository<Usuario, Long>{

	@Query("SELECT CONCAT(u.nombre,' ',u.apellido) FROM Usuario u WHERE u.idUsuario=?1")
	public String findNombreUsuario(Long idUsuario);
	
}
