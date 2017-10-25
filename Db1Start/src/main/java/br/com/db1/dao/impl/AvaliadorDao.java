package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.Avaliador;

public class AvaliadorDao implements DAO<Avaliador> {

	@Inject
	private EntityManager manager;

	public List<Avaliador> findAll() {
		return manager.createQuery("Select a from Avaliador a").getResultList();
	}

	public Avaliador findById(Long id) {
		Query query = manager.createQuery("Select a from Avaliador a where a.id = :pId");
		query.setParameter("pId", id);
		return (Avaliador) query.getSingleResult();
	}

	public List<Avaliador> findByName(String nome) {
		Query query = manager.createQuery("Select a from Avaliador a where a.nome like :pNome");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}

	@Transactional
	public boolean save(Avaliador avaliador) {
		try {
			if (avaliador.getId() != null) {
				manager.merge(avaliador);
			} else {
				manager.persist(avaliador);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		Avaliador avaliador = findById(id);
		try {
			manager.remove(avaliador);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
