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

import br.com.db1.dao.impl.TipoAvaliacaoDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.TipoAvaliacao;

@ApplicationScoped
@Named
public class TipoAvaliacaoBean {

	@Inject
	private TipoAvaliacaoDao dao;

	private List<TipoAvaliacao> list;

	private String nomeTipoAvaliacaoFiltrado;

	private TipoAvaliacao tipoAvaliacao;

	@PostConstruct
	public void init() {
		zerarLista();
		this.tipoAvaliacao = new TipoAvaliacao();
		this.nomeTipoAvaliacaoFiltrado = "";
	}

	private void zerarLista() {
		list = new ArrayList<TipoAvaliacao>();
	}

	public String getNomeCidadeFiltrada() {
		return nomeTipoAvaliacaoFiltrado;
	}

	public void setNomeCidadeFiltrada(String nomeCidadeFiltrada) {
		this.nomeTipoAvaliacaoFiltrado = nomeCidadeFiltrada;
	}

	public TipoAvaliacaoDao getDao() {
		return dao;
	}

	public void setDao(TipoAvaliacaoDao dao) {
		this.dao = dao;
	}

	public List<TipoAvaliacao> getList() {
		return list;
	}

	public void setList(List<TipoAvaliacao> list) {
		this.list = list;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public String novo() {
		this.tipoAvaliacao = new TipoAvaliacao();
		return "cadastrarTipoAvaliacao";
	}

	public String salvar() {
		if (!dao.save(this.tipoAvaliacao)) {
			adicionarMensagem("Erro ao cadastrar o tipoAvaliacao.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("TipoAvaliacao salvo com sucesso.", FacesMessage.SEVERITY_INFO);
			this.tipoAvaliacao = new TipoAvaliacao();
		}
		return "tipoAvaliacao";
	}

	public String editar(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = dao.findById(tipoAvaliacao.getId());
		return "cadastrarAvaliacao";
	}

	public String remover(TipoAvaliacao tipoAvaliacao) {
		if (!dao.delete(tipoAvaliacao.getId())) {
			adicionarMensagem("Erro ao remover o tipoAvaliacao.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("TipoAvaliacao removido com sucesso.", FacesMessage.SEVERITY_INFO);
			listarTipoAvaliacao();
		}
		return "tipoAvaliacao";
	}

	public void listarTipoAvaliacao() {
		zerarLista();
		if (!nomeTipoAvaliacaoFiltrado.isEmpty()) {
			list.addAll(dao.findByName(nomeTipoAvaliacaoFiltrado));
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
