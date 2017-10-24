package br.com.db1.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.db1.dao.impl.AvaliadorDao;
import br.com.db1.model.Avaliador;

@RequestScoped
@Named
public class AvaliadorBean {

	@Inject
	private AvaliadorDao dao;

	private List<Avaliador> list;

	private String nomeAvaliadorFiltrado;

	private Avaliador avaliador;

	@PostConstruct
	public void init() {
		zerarLista();
	}

	private void zerarLista() {
		list = new ArrayList<Avaliador>();
	}

	public String getNomeCidadeFiltrada() {
		return nomeAvaliadorFiltrado;
	}

	public void setNomeCidadeFiltrada(String nomeCidadeFiltrada) {
		this.nomeAvaliadorFiltrado = nomeCidadeFiltrada;
	}

	public AvaliadorDao getDao() {
		return dao;
	}

	public void setDao(AvaliadorDao dao) {
		this.dao = dao;
	}

	public List<Avaliador> getList() {
		return list;
	}

	public void setList(List<Avaliador> list) {
		this.list = list;
	}

	public String getNomeAvaliadorFiltrado() {
		return nomeAvaliadorFiltrado;
	}

	public void setNomeAvaliadorFiltrado(String nomeAvaliadorFiltrado) {
		this.nomeAvaliadorFiltrado = nomeAvaliadorFiltrado;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
	}

	public String novo() {
		this.avaliador = new Avaliador();
		return "avaliadorCadastro";
	}

	public String salvar() {
		System.out.println("entrei no save porra");
		if(dao == null) {System.out.println("esta merda ta vazia");}
		if (!dao.save(this.avaliador)) {
			adicionarMensagem("Erro ao cadastrar o avaliador.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Avaliador salvo com sucesso.", FacesMessage.SEVERITY_INFO);
		}
		return "avaliador";
	}

	public String editar(Avaliador avaliador) {
		this.avaliador = dao.findById(avaliador.getId());
		return "cadastrarCidade";
	}

	public String remover(Avaliador avaliador) {
		if (!dao.delete(avaliador.getId())) {
			adicionarMensagem("Erro ao remover o avaliador.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Avaliador removido com sucesso.", FacesMessage.SEVERITY_INFO);
			listarAvaliador();
		}
		return "avaliador";
	}

	public void listarAvaliador() {
		zerarLista();
		if (!nomeAvaliadorFiltrado.isEmpty()) {
			list.addAll(dao.findByName(nomeAvaliadorFiltrado));
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
