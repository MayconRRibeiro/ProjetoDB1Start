package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.ResultadoProva;
import br.com.db1.model.ResultadoProva;

public class ResultadoProvaDao implements DAO<ResultadoProva> {

	private EntityManager manager;

	@Inject
	public ResultadoProvaDao(EntityManager manager) {
		this.manager = manager;
	}

	public List<ResultadoProva> findAll() {
		return manager.createQuery("Select r from ResultadoProva r").getResultList();
	}

	public ResultadoProva findById(Long id) {
		Query query = manager.createQuery("Select r from ResultadoProva r where r.id = :pId");
		query.setParameter("pId", id);
		return (ResultadoProva) query.getSingleResult();
	}

	public List<ResultadoProva> findByName(String parecer) {
		Query query = manager.createQuery("Select r from ResultadoProva r where r.parecer like :pParecer");
		query.setParameter("pParecer", "%" + parecer + "%");
		return query.getResultList();
	}

	@Transactional
	public boolean save(ResultadoProva ResultadoProva) {
		try {
			if (ResultadoProva.getId() != null) {
				manager.merge(ResultadoProva);
			} else {
				manager.persist(ResultadoProva);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		ResultadoProva ResultadoProva = findById(id);
		try {
			manager.remove(ResultadoProva);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
