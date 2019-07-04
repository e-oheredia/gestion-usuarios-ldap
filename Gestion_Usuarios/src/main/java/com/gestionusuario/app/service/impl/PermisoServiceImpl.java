package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.repository.PermisoDAO;
import com.gestionusuario.app.service.PermisoService;


@Service("permisoservice")
public class PermisoServiceImpl implements PermisoService{
	
	
	@Autowired
	private PermisoDAO permisodao;
	
	public PermisoDAO getPermisodao() {
		return permisodao;
	}

	public void setPermisodao(PermisoDAO permisodao) {
		this.permisodao = permisodao;
	}

	
	@Override
	public String ObtenerPermisosID(Long idUsuario) throws Exception {
		try {
			return this.getPermisodao().ObtenerPermisosID(idUsuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public String ObtenerPermisosNombre(int idPermiso) throws Exception {
		try {
			return this.getPermisodao().ObtenerPermisosNombre(idPermiso);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
