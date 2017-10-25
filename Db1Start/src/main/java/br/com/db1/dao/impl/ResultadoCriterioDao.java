package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.ResultadoCriterio;

public class ResultadoCriterioDao implements DAO<ResultadoCriterio> {

	@Inject
	private EntityManager manager;

	public List<ResultadoCriterio> findAll() {
		return manager.createQuery("Select r from ResultadoCriterio r").getResultList();
	}

	public ResultadoCriterio findById(Long id) {
		Query query = manager.createQuery("Select r from ResultadoCriterio r where r.id = :pId");
		query.setParameter("pId", id);
		return (ResultadoCriterio) query.getSingleResult();
	}

	public List<ResultadoCriterio> findByName(String nome) {
		Query query = manager.createQuery("Select r from ResultadoCriterio r where r.nome like :pNome");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}

	@Transactional
	public boolean save(ResultadoCriterio resultadoCriterio) {
		try {
			if (resultadoCriterio.getId() != null) {
				manager.merge(resultadoCriterio);
			} else {
				manager.persist(resultadoCriterio);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		ResultadoCriterio resultadoCriterio = findById(id);
		try {
			manager.remove(resultadoCriterio);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
