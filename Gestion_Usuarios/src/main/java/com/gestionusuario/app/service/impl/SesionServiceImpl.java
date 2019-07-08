package com.gestionusuario.app.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionusuario.app.dao.ISesionDao;
import com.gestionusuario.app.entity.Sesion;
import com.gestionusuario.app.entity.Tipo_Autenticacion;
import com.gestionusuario.app.repository.SesionDAO;
import com.gestionusuario.app.service.SesionService;

@Service("sesionservice")
public class SesionServiceImpl implements SesionService {
	
	@Autowired
	private SesionDAO sesiondao;
	
	@Autowired
	private ISesionDao sesionDao;
	
	public SesionDAO getSesiondao() {
		return sesiondao;
	}

	public void setSesiondao(SesionDAO sesiondao) {
		this.sesiondao = sesiondao;
	}

	@Override
	public Long CrearSesion(String matricula, Long tipoAutenticacion) throws Exception {
		
		Sesion sesionNueva = new Sesion();
		Tipo_Autenticacion tipoAut = new Tipo_Autenticacion();
		tipoAut.setIdTipo_Autenticacion(tipoAutenticacion);
		sesionNueva.setMatriculaUsuario(matricula);
		sesionNueva.setInisesion(new Date());
		sesionNueva.setAutsesion(tipoAut);
		Sesion sesionCreada = new Sesion();
		sesionCreada = sesionDao.save(sesionNueva);
		
		return sesionCreada.getIdSesion();
		
//		try {
//			return this.getSesiondao().CrearSesion(idUsuario);
//		} catch (Exception e) {
//			throw new Exception();
//		}
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
