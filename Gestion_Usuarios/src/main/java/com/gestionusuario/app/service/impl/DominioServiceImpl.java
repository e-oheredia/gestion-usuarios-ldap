package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.dao.IDominioDao;
import com.gestionusuario.app.entity.Dominio;
import com.gestionusuario.app.service.DominioService;

@Service
public class DominioServiceImpl implements DominioService {
	
	@Autowired
	IDominioDao dominioDao;

	@Override
	public Iterable<Dominio> listarActivos() {
		return dominioDao.findByActivoTrue();
	}

	@Override
	public Dominio listarById(Long id) {
		return dominioDao.findById(id).orElse(null);
	}

}
