package com.gestionusuario.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.repository.SesionDAO;
import com.gestionusuario.app.service.SesionService;

@Service("sesionservice")
public class SesionServiceImpl implements SesionService {
	
	@Autowired
	private SesionDAO sesiondao;
	
	public SesionDAO getSesiondao() {
		return sesiondao;
	}

	public void setSesiondao(SesionDAO sesiondao) {
		this.sesiondao = sesiondao;
	}

	@Override
	public int CrearSesion(Long idUsuario) throws Exception {
		try {
			return this.getSesiondao().CrearSesion(idUsuario);
		} catch (Exception e) {
			throw new Exception();
		}
	}

	@Override
	public int CerrarSesion(int idSesion) throws Exception {
		try {
			return this.getSesiondao().CerrarSesion(idSesion);
		} catch (Exception e) {
			throw new Exception();
		}
	}

}
