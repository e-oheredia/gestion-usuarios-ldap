package com.gestionusuario.app.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.entity.Sesion;

@Repository
public interface ISesionDao extends CrudRepository<Sesion, Long>{

	
}
