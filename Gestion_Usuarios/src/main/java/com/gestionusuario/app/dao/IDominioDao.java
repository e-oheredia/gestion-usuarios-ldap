package com.gestionusuario.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Dominio;

@Repository
public interface IDominioDao extends CrudRepository<Dominio, Long> {
	
	Iterable<Dominio> findByActivoTrue();
}
