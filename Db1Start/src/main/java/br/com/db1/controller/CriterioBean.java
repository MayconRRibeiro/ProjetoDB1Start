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

import br.com.db1.dao.impl.CriterioDao;
import br.com.db1.model.Criterio;

@ApplicationScoped
@Named
public class CriterioBean {

	private CriterioDao dao;

	private List<Criterio> list;

	private String nomeCriterioFiltrado;

	private Criterio criterio;

	private Boolean mostrar;

	@Inject
	public CriterioBean(CriterioDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void init() {
		zerarLista();
		this.criterio = new Criterio();
		this.nomeCriterioFiltrado = "";
		this.mostrar = false;
		listarCriterio();
	}

	private void zerarLista() {
		list = new ArrayList<Criterio>();
	}

	public String getNomeCidadeFiltrada() {
		return nomeCriterioFiltrado;
	}

	public void setNomeCidadeFiltrada(String nomeCidadeFiltrada) {
		this.nomeCriterioFiltrado = nomeCidadeFiltrada;
	}

	public CriterioDao getDao() {
		return dao;
	}

	public void setDao(CriterioDao dao) {
		this.dao = dao;
	}

	public List<Criterio> getList() {
		return list;
	}

	public void setList(List<Criterio> list) {
		this.list = list;
	}

	public Criterio getCriterio() {
		return criterio;
	}

	public void setCriterio(Criterio criterio) {
		this.criterio = criterio;
	}

	public String novo() {
		this.criterio = new Criterio();
		return "cadastrarCriterio";
	}

	public String salvar() {
		if (!dao.save(this.criterio)) {
			adicionarMensagem("Erro ao cadastrar o criterio.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Criterio salvo com sucesso.", FacesMessage.SEVERITY_INFO);
			nomeCriterioFiltrado = this.criterio.getDescricao();
			this.criterio = new Criterio();
		}
		this.mostrar = false;
		listarCriterio();
		return "criterioCadastro";
	}

	public String editar(Criterio criterio) {
		this.criterio = dao.findById(criterio.getId());
		return "criterioCadastro";
	}

	public String remover(Criterio criterio) {
		if (!dao.delete(criterio.getId())) {
			adicionarMensagem("Erro ao remover o criterio.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Criterio removido com sucesso.", FacesMessage.SEVERITY_INFO);
			listarCriterio();
		}
		listarCriterio();
		return "criterioCadastro";
	}

	public void listarCriterio() {
		zerarLista();
		if (!nomeCriterioFiltrado.isEmpty()) {
			list.addAll(dao.findByName(nomeCriterioFiltrado));
		} else {
			list.addAll(dao.findAll());
		}
	}

	public void adicionarMensagem(String mensagem, Severity tipoMensagem) {
		FacesContext fc = FacesContext.getCurrentInstance();
		FacesMessage fm = new FacesMessage(mensagem);
		fm.setSeverity(tipoMensagem);
		fc.addMessage(null, fm);

	}

	public Boolean getMostrar() {
		return mostrar;
	}

	public void setMostrar(Boolean mostrar) {
		this.mostrar = mostrar;
	}

}
