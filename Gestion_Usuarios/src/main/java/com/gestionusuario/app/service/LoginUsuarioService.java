package com.gestionusuario.app.service;

import java.util.List;

import com.gestionusuario.app.entity.Usuario;

public interface LoginUsuarioService {
	
	//Usuario save(UserDto user);
    List<Usuario> findAll();
    void delete(long id);
    Usuario findOne(String username);

    Usuario findById(Long idUsuario);

}
