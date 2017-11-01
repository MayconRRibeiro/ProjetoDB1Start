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
import br.com.db1.dao.impl.ResultadoCriterioDao;
import br.com.db1.model.Avaliador;
import br.com.db1.model.Prova;
import br.com.db1.model.ResultadoProva;

@ApplicationScoped
@Named
public class CorrecoesBean {

}
