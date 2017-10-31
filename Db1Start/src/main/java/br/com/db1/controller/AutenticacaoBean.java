package br.com.db1.controller;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.db1.dao.impl.UsuarioDao;
import br.com.db1.model.Usuario;

@ManagedBean
public class AutenticacaoBean {

	private UsuarioDao dao;

	private Usuario usuario;

	@Inject
	public AutenticacaoBean(UsuarioDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void init() {
		usuario = new Usuario();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String autenticar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		usuario = dao.autenticacao(usuario.getEmail(), usuario.getSenha());
		if (usuario == null) {
			fc.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário não encontrado!", "Erro no Login!"));
			return null;
		}

		if (usuario.getPrivilegio() == true) {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);
			return "/rhHome";

		} else {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", this.usuario);
			return "/avaliadorHome";
		}
	}

	public String registraSaida() {
		usuario = new Usuario();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");

		return "/login";
	}

}