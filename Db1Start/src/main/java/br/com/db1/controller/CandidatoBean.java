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

import br.com.db1.dao.impl.CandidatoDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.Candidato;

@ApplicationScoped
@Named
public class CandidatoBean {

	@Inject
	private CandidatoDao dao;

	private List<Candidato> list;

	private String nomeCandidatoFiltrado;

	private Candidato candidato;

	@PostConstruct
	public void init() {
		zerarLista();
		this.candidato = new Candidato();
		this.nomeCandidatoFiltrado =""; 
	}

	private void zerarLista() {
		list = new ArrayList<Candidato>();
	}

	public String getNomeCidadeFiltrada() {
		return nomeCandidatoFiltrado;
	}

	public void setNomeCidadeFiltrada(String nomeCidadeFiltrada) {
		this.nomeCandidatoFiltrado = nomeCidadeFiltrada;
	}

	public CandidatoDao getDao() {
		return dao;
	}

	public void setDao(CandidatoDao dao) {
		this.dao = dao;
	}

	public List<Candidato> getList() {
		return list;
	}

	public void setList(List<Candidato> list) {
		this.list = list;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public String novo() {
		this.candidato = new Candidato();
		return "cadastrarCandidato";
	}

	public String salvar() {
		if (!dao.save(this.candidato)) {
			adicionarMensagem("Erro ao cadastrar o candidato.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Candidato salvo com sucesso.", FacesMessage.SEVERITY_INFO);
			this.candidato = new Candidato();
		}
		return "candidato";
	}

	public String editar(Candidato candidato) {
		this.candidato = dao.findById(candidato.getId());
		return "cadastrarCandidato";
	}

	public String remover(Candidato candidato) {
		if (!dao.delete(candidato.getId())) {
			adicionarMensagem("Erro ao remover o candidato.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Candidato removido com sucesso.", FacesMessage.SEVERITY_INFO);
			listarCandidato();
		}
		return "candidato";
	}

	public void listarCandidato() {
		zerarLista();
		if (!nomeCandidatoFiltrado.isEmpty()) {
			list.addAll(dao.findByName(nomeCandidatoFiltrado));
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

}
