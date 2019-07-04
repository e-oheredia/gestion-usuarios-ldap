package com.gestionusuario.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Usuario;

@Repository
public interface LoginUsuarioDAO extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);
	
}
