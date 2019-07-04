package com.gestionusuario.app.service;

import com.gestionusuario.app.entity.Dominio;

public interface DominioService {
	Iterable<Dominio> listarActivos();
	Dominio listarById(Long id);
}
