package com.gestionusuario.app.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.entity.Usuario;
import com.gestionusuario.app.repository.LoginUsuarioDAO;
import com.gestionusuario.app.service.LoginUsuarioService;



@Service(value = "loginUsuarioService")
public class LoginUsuarioServiceImpl implements UserDetailsService, LoginUsuarioService {
	
	
	@Autowired
	private LoginUsuarioDAO loginusuariodao;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, RuntimeException {
		Usuario usuario = loginusuariodao.findByUsername(username);
		
		
		if(usuario==null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(usuario.getUsername(), usuario.getPassword(), getAuthority());
	}
	
	private List<SimpleGrantedAuthority> getAuthority() {
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
	}

	@Override
	public List<Usuario> findAll() {
		List<Usuario> list = new ArrayList<>();
		loginusuariodao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario findOne(String username) {
		return loginusuariodao.findByUsername(username);
	}

	@Override
	public Usuario findById(Long idUsuario) {
		return null;
	}

	

}
