package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.Candidato;

public class CandidatoDao implements DAO<Candidato> {

	
	private EntityManager manager;

	@Inject
	public CandidatoDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Candidato> findAll() {
		return manager.createQuery("Select c from Candidato c").getResultList();
	}

	public Candidato findById(Long id) {
		Query query = manager.createQuery("Select c from Candidato c where c.id = :pId");
		query.setParameter("pId", id);
		return (Candidato) query.getSingleResult();
	}

	public List<Candidato> findByName(String nome) {
		Query query = manager.createQuery("Select c from Candidato c where c.nome like :pNome");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}

	@Transactional
	public boolean save(Candidato candidato) {
		try {
			if (candidato.getId() != null) {
				manager.merge(candidato);
			} else {
				manager.persist(candidato);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		Candidato candidato = findById(id);
		try {
			manager.remove(candidato);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
