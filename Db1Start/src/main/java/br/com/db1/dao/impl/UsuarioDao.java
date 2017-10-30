package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.db1.dao.DAO;
import br.com.db1.dao.Transactional;
import br.com.db1.model.Avaliador;
import br.com.db1.model.Usuario;

public class UsuarioDao implements DAO<Usuario> {

	private EntityManager manager;
	
	@Inject
	public UsuarioDao(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Usuario> findAll() {
		return manager.createQuery("Select a from Usuario a").getResultList();
	}

	public Usuario findById(Long id) {
		Query query = manager.createQuery("Select a from Usuario a where a.id = :pId");
		query.setParameter("pId", id);
		return (Usuario) query.getSingleResult();
	}

	public List<Usuario> findByName(String nome) {
		Query query = manager.createQuery("Select a from Avaliador a where a.nome like :pNome");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}
	
	public List<Avaliador> findByTipo(String tipo) {
		Query query = manager.createQuery("Select a from Avaliador a where a.tipo like :pTipo");
		query.setParameter("pTipo", "%" + tipo + "%");
		return query.getResultList();
	}

	@Transactional
	public boolean save(Usuario usuario) {
		try {
			if (usuario.getId() != null) {
				manager.merge(usuario);
			} else {
				manager.persist(usuario);
			}
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean delete(Long id) {
		Usuario usuario = findById(id);
		try {
			manager.remove(usuario);
		} catch (Exception e) {
			return false;
		}
		return true;

	}

}
