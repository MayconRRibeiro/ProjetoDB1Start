package br.com.db1.dao.impl;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
		return manager.createQuery("Select u from Usuario u").getResultList();
	}

	public Usuario findById(Long id) {
		Query query = manager.createQuery("Select u from Usuario u where u.id = :pId");
		query.setParameter("pId", id);
		return (Usuario) query.getSingleResult();
	}

	public List<Usuario> findByName(String nome) {
		Query query = manager.createQuery("Select u from Usuario u where lower(u.nome) like lower(:pNome)");
		query.setParameter("pNome", "%" + nome + "%");
		return query.getResultList();
	}

	public Usuario findByEmail(String email) {
		try {
			Query query = manager.createQuery("Select u from Usuario u where lower(u.email) like lower(:pEmail)");
			query.setParameter("pEmail", email);
			return (Usuario) query.getSingleResult();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;

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

	public Usuario autenticacao(String email, String senha) {
		try {
			Query query = manager.createQuery("Select u from Usuario u where u.email = :pEmail and u.senha = :pSenha");
			query.setParameter("pEmail", email);
			query.setParameter("pSenha", senha);
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;

		}

	}

}
