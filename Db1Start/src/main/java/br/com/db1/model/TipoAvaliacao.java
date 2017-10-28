package br.com.db1.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tipoAvaliacao", schema = "public")
public class TipoAvaliacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 50, nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private Integer prazo;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoAvaliacao")
	private List<Criterio> criterio;
	
	@OneToOne
	private Prova prova;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
	}

	public List<Criterio> getCriterio() {
		return criterio;
	}

	public void setCriterio(List<Criterio> criterio) {
		this.criterio = criterio;
	}
}