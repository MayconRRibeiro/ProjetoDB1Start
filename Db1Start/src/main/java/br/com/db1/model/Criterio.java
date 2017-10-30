package br.com.db1.model;

import java.io.Serializable;

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
public class Criterio implements Serializable {

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((obrigatorio == null) ? 0 : obrigatorio.hashCode());
		result = prime * result + ((resultadoCriterio == null) ? 0 : resultadoCriterio.hashCode());
		result = prime * result + ((tipoAvaliacao == null) ? 0 : tipoAvaliacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criterio other = (Criterio) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (obrigatorio == null) {
			if (other.obrigatorio != null)
				return false;
		} else if (!obrigatorio.equals(other.obrigatorio))
			return false;
		if (resultadoCriterio == null) {
			if (other.resultadoCriterio != null)
				return false;
		} else if (!resultadoCriterio.equals(other.resultadoCriterio))
			return false;
		if (tipoAvaliacao == null) {
			if (other.tipoAvaliacao != null)
				return false;
		} else if (!tipoAvaliacao.equals(other.tipoAvaliacao))
			return false;
		return true;
	}

}