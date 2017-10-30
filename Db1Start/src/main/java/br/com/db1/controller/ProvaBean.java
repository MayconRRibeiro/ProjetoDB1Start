package br.com.db1.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import br.com.db1.dao.impl.AvaliadorDao;
import br.com.db1.dao.impl.ProvaDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.Prova;

@ApplicationScoped
@Named
public class ProvaBean {

	private ProvaDao dao;

	private List<Prova> list;

	private Prova prova;

	private Boolean mostrar;

	private Part arquivoUpado;

	@Inject
	public ProvaBean(ProvaDao dao) {
		this.dao = dao;
	}

	@PostConstruct
	public void init() {
		zerarLista();
		this.prova = new Prova();
		this.mostrar = false;
		listarProva();
	}

	public Part getArquivoUpado() {
		return arquivoUpado;
	}

	public void setArquivoUpado(Part arquivoUpado) {
		this.arquivoUpado = arquivoUpado;
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

	private String getNomeArquivo() {
		String header = arquivoUpado.getHeader("content-disposition");
		if (header == null)
			return "";
		for (String headerPart : header.split(";")) {
			if (headerPart.trim().startsWith("filename")) {
				return headerPart.substring(headerPart.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return "";
	}

	public void download(Prova arquivoParametro) throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		externalContext.setResponseHeader("Content-Type", arquivoParametro.getExtensaoArquivo());
		externalContext.setResponseHeader("Content-Length", "" + arquivoParametro.getAvaliacao().length);
		externalContext.setResponseHeader("Content-Disposition",
				"attachment;filename=\"" + arquivoParametro.getNomeArquivo() + "\"");
		externalContext.getResponseOutputStream().write(arquivoParametro.getAvaliacao());
		facesContext.responseComplete();
	}

	public String salvar() {
		try {
			this.prova.setNomeArquivo(getNomeArquivo());
			this.prova.setExtensaoArquivo(arquivoUpado.getContentType());

			byte[] arquivoByte = IOUtils.toByteArray(arquivoUpado.getInputStream());
			this.prova.setAvaliacao(arquivoByte);

			if (!dao.save(this.prova)) {
				adicionarMensagem("Erro ao cadastrar o prova.", FacesMessage.SEVERITY_ERROR);
			} else {
				adicionarMensagem("Prova salvo com sucesso.", FacesMessage.SEVERITY_INFO);
				this.prova = new Prova();
			}
		} catch (IOException e) {
			adicionarMensagem("Erro ao enviar o arquivo " + e.getMessage(), FacesMessage.SEVERITY_ERROR);
		}

		this.mostrar = false;
		listarProva();
		return "provaCadastro";
	}

	public String editar(Prova prova) {
		this.prova = dao.findById(prova.getId());
		this.mostrar = true;
		return "provaCadastro";
	}

	public String remover(Prova prova) {
		if (!dao.delete(prova.getId())) {
			adicionarMensagem("Erro ao remover o prova.", FacesMessage.SEVERITY_ERROR);
		} else {
			adicionarMensagem("Prova removido com sucesso.", FacesMessage.SEVERITY_INFO);
		}
		listarProva();
		return "provaCadastro";
	}

	public void listarProva() {
		zerarLista();
		list.addAll(dao.findAll());
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
