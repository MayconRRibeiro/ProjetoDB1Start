package br.com.db1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.db1.dao.impl.UsuarioDao;
import br.com.db1.model.Usuario;

@ApplicationScoped
@Named
public class UsuarioBean {
	@Inject
	private UsuarioDao dao;

	private List<Usuario> list;

	private Usuario usuario;

	private String acaoUsuario;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		list = new ArrayList<Usuario>();
	}

	public String getAcaoUsuario() {
		return acaoUsuario;
	}

	public void setAcaoUsuario(String acaoUsuario) {
		this.acaoUsuario = acaoUsuario;
	}

	private void zerarLista() {
		list = new ArrayList<Usuario>();
	}

	public List<Usuario> getList() {
		return list;
	}

	public void setList(List<Usuario> list) {
		this.list = list;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void adicionarMensagem(String mensagem, Severity tipoMensagem) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(mensagem);
		fm.setSeverity(tipoMensagem);
		fc.addMessage(null, fm);

	}

}