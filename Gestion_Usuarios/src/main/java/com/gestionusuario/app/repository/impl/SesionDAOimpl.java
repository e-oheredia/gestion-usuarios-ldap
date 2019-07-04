package com.gestionusuario.app.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gestionusuario.app.repository.SesionDAO;


@Repository("sesiondao")
public class SesionDAOimpl implements SesionDAO {

	@PersistenceContext
	private EntityManager em;
	
	
	@Override
	public int CrearSesion(Long idUsuario) throws Exception {
		int rpta=0;

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("sesion.CrearSesion");
			spq.setParameter("idUsuario", idUsuario);
			spq.execute();
			Object ret = spq.getOutputParameterValue("usuarioActual");
			rpta=Integer.parseInt(ret.toString());
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rpta;
	}

	@Override
	public int CerrarSesion(int idSesion) throws Exception {
		int rpta=0;

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("sesion.CerrarSesion");
			spq.setParameter("idSesion", idSesion);
			spq.execute();
			rpta=1;
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rpta;
	}

}
