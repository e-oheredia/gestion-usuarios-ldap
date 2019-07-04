package com.gestionusuario.app.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import org.springframework.stereotype.Repository;

import com.gestionusuario.app.repository.PermisoDAO;

@Repository("permisodao")
public class PermisoDAOimpl implements PermisoDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public String ObtenerPermisosID(Long idUsuario) throws Exception {

		String rpta = " ";

		try {

			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("permisos.ObtenerPermisosID");
			spq.setParameter("idUsuario", idUsuario);
			spq.execute();
			em.close();
			Object ret = spq.getOutputParameterValue("Permisos");
			rpta = ret.toString();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rpta;
	}

	@Override
	public String ObtenerPermisosNombre(int idPermiso) throws Exception {
		String rpta = "";

		try {
			StoredProcedureQuery spq = em.createNamedStoredProcedureQuery("permisos.ObtenerPermisosNombre");
			spq.setParameter("idPermiso", idPermiso);
			spq.execute();
			em.close();
			Object ret = spq.getOutputParameterValue("nombre");
			rpta = ret.toString();
			em.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return rpta;
	}

}
