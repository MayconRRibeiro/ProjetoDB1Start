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

import br.com.db1.dao.impl.ResultadoCriterioDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.ResultadoCriterio;

@ApplicationScoped
@Named
public class ResultadoCriterioBean {

	@Inject
	private ResultadoCriterioDao dao;

	private List<ResultadoCriterio> list;

	private String nomeResultadoCriterioFiltrado;

	private ResultadoCriterio resultadoCriterio;
	
	private Boolean mostar;

	@PostConstruct
	public void init() {
		zerarLista();
		this.resultadoCriterio= new ResultadoCriterio();
		this.nomeResultadoCriterioFiltrado = "";
		this.mostar = false;
		listarResultadoCriterio();
	}

	private void zerarLista() {
		list = new ArrayList<ResultadoCriterio>();
	}

	public String getNomeCidadeFiltrada() {
		return nomeResultadoCriterioFiltrado;
	}

	public void setNomeCidadeFiltrada(String nomeCidadeFiltrada) {
		this.nomeResultadoCriterioFiltrado = nomeCidadeFiltrada;
	}

	public ResultadoCriterioDao getDao() {
		return dao;
	}

	public void setDao(ResultadoCriterioDao dao) {
		this.dao = dao;
	}

	public List<ResultadoCriterio> getList() {
		return list;
	}

	public void setList(List<ResultadoCriterio> list) {
		this.list = list;
	}

	public ResultadoCriterio getResultadoCriterio() {
		return resultadoCriterio;
	}

	public void setResultadoCriterio(ResultadoCriterio resultadoCriterio) {
		this.resultadoCriterio = resultadoCriterio;
	}

	public String novo() {
		this.resultadoCriterio = new ResultadoCriterio();
		return "cadastrarResultadoCriterio";
	}

	public String salvar() {
		if (!dao.save(this.resultadoCriterio)) {
			adicionarMensagem("Erro ao cadastrar o resultadoCriterio.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("ResultadoCriterio salvo com sucesso.", FacesMessage.SEVERITY_INFO);
			this.resultadoCriterio = new ResultadoCriterio();
		}
		this.mostar = false;
		listarResultadoCriterio();
		return "resultadoCriterio";
	}

	public String editar(ResultadoCriterio resultadoCriterio) {
		this.resultadoCriterio = dao.findById(resultadoCriterio.getId());
		return "cadastrarCriterio";
	}

	public String remover(ResultadoCriterio resultadoCriterio) {
		if (!dao.delete(resultadoCriterio.getId())) {
			adicionarMensagem("Erro ao remover o resultadoCriterio.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("ResultadoCriterio removido com sucesso.", FacesMessage.SEVERITY_INFO);
			listarResultadoCriterio();
		}
		listarResultadoCriterio();
		return "resultadoCriterio";
	}

	public void listarResultadoCriterio() {
		zerarLista();
		if (!nomeResultadoCriterioFiltrado.isEmpty()) {
			list.addAll(dao.findByName(nomeResultadoCriterioFiltrado));
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

	public Boolean getMostar() {
		return mostar;
	}

	public void setMostar(Boolean mostar) {
		this.mostar = mostar;
	}

}
