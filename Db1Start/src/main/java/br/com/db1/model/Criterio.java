package br.com.db1.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "criterio", schema = "public")
public class Criterio {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20)
	private String descricao;

	@Column(nullable = false)
	private Boolean obrigatorio;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipoAvaliacao_id", nullable = false)
	private TipoAvaliacao tipoAvaliacao;

	@OneToOne
	private ResultadoCriterio resultadoCriterio;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean getObrigatorio() {
		return obrigatorio;
	}

	public void setObrigatorio(Boolean obrigatorio) {
		this.obrigatorio = obrigatorio;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public ResultadoCriterio getResultadoCriterio() {
		return resultadoCriterio;
	}

	public void setResultadoCriterio(ResultadoCriterio resultadoCriterio) {
		this.resultadoCriterio = resultadoCriterio;
	}

}