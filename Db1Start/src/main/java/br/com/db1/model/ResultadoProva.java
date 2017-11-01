package br.com.db1.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "resultadoProva", schema = "public")
public class ResultadoProva {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 30)
	private String pontosFortes;

	@Column(length = 30)
	private String pontosFracos;

	@Column(length = 50)
	private String parecer;

	@Column(name = "data_correcao")
	@Temporal(TemporalType.DATE)
	private Date dataCorrecao;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "prova_id", nullable = false)
	private Prova Prova;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPontosFortes() {
		return pontosFortes;
	}

	public void setPontosFortes(String pontosFortes) {
		this.pontosFortes = pontosFortes;
	}

	public String getPontosFracos() {
		return pontosFracos;
	}

	public void setPontosFracos(String pontosFracos) {
		this.pontosFracos = pontosFracos;
	}

	public String getParecer() {
		return parecer;
	}

	public void setParecer(String parecer) {
		this.parecer = parecer;
	}

	public Date getDataCorrecao() {
		return dataCorrecao;
	}

	public void setDataCorrecao(Date dataCorrecao) {
		this.dataCorrecao = dataCorrecao;
	}

	public Prova getProva() {
		return Prova;
	}

	public void setProva(Prova prova) {
		Prova = prova;
	}

	
	
}