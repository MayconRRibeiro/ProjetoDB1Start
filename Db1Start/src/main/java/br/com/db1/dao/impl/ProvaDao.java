package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.Criterio;
import br.com.db1.model.Prova;
import br.com.db1.model.ResultadoCriterio;

public class ProvaDao implements DAO<Prova> {

	
	private EntityManager manager;

	@Inject
	public ProvaDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Prova> findAll() {
		return manager.createQuery("Select p from Prova p").getResultList();
	}

	public Prova findById(Long id) {
		Query query = manager.createQuery("Select p from Prova p where p.id = :pId");
		query.setParameter("pId", id);
		return (Prova) query.getSingleResult();
	}

	public List<Prova> findByName(String nome) {
		Query query = manager.createQuery("Select p from Prova p where p.nome like :pNome");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}
	
	public List<Criterio> findCriterioByTipoAvaliacao(Long tipo) {
		Query query = manager.createQuery("Select c from Criterio c where c.tipoAvaliacao_id like :pTipo");
		query.setParameter("pTipo", "%" + tipo + "%");
		return query.getResultList();
	}
	
	public ResultadoCriterio findResultadoCriterio(Long prova, Long criterio) {
		Query query = manager.createQuery("Select r from ResultadoCriterio r where r.criterio_id like :pCriterio and "
																				+ "r.prova_id like :pProva");
		query.setParameter("pProva", "%" + prova + "%");
		query.setParameter("pCriterio", "%" + criterio + "%");
		return (ResultadoCriterio) query.getResultList();
	}

	@Transactional
	public boolean save(Prova prova) {
		try {
			if (prova.getId() != null) {
				manager.merge(prova);
			} else {
				manager.persist(prova);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		Prova prova = findById(id);
		try {
			manager.remove(prova);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
