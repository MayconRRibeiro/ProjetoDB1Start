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

import br.com.db1.dao.impl.ProvaDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.Prova;

@ApplicationScoped
@Named
public class ProvaBean {

	@Inject
	private ProvaDao dao;

	private List<Prova> list;

	private Prova prova;

	@PostConstruct
	public void init() {
		zerarLista();
		this.prova= new Prova();

	}

	private void zerarLista() {
		list = new ArrayList<Prova>();
	}

	public ProvaDao getDao() {
		return dao;
	}

	public void setDao(ProvaDao dao) {
		this.dao = dao;
	}

	public List<Prova> getList() {
		return list;
	}

	public void setList(List<Prova> list) {
		this.list = list;
	}

	public Prova getProva() {
		return prova;
	}

	public void setProva(Prova prova) {
		this.prova = prova;
	}

	public String novo() {
		this.prova = new Prova();
		return "cadastrarProva";
	}

	public String salvar() {
		if (!dao.save(this.prova)) {
			adicionarMensagem("Erro ao cadastrar o prova.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Prova salvo com sucesso.", FacesMessage.SEVERITY_INFO);
			this.prova = new Prova();
		}
		return "prova";
	}

	public String editar(Prova prova) {
		this.prova = dao.findById(prova.getId());
		return "cadastrarProva";
	}

	public String remover(Prova prova) {
		if (!dao.delete(prova.getId())) {
			adicionarMensagem("Erro ao remover o prova.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Prova removido com sucesso.", FacesMessage.SEVERITY_INFO);
		}
		return "prova";
	}

	public void adicionarMensagem(String mensagem, Severity tipoMensagem) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(mensagem);
		fm.setSeverity(tipoMensagem);
		fc.addMessage(null, fm);

	}

}
