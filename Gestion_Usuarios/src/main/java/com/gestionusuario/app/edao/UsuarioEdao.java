package com.gestionusuario.app.edao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.gestionusuario.app.request.Requester;

@Repository
public class UsuarioEdao {
	
	@Value("${service.ldap}")
	private String ldapPath;
	
	@Autowired
	private Requester requester;
	
}
