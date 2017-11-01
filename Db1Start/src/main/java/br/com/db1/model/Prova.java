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
@Table(name = "prova", schema = "public")
public class Prova {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = true)
	private byte[] avaliacao;

	@Column(name = "nome", nullable = true, length = 50)
	private String nomeArquivo;

	@Column(name = "extensao", nullable = true, length = 10)
	private String extensaoArquivo;

	@Column(nullable = false, name = "data_avaliacao")
	@Temporal(TemporalType.DATE)
	private Date dataAvaliacao;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "candidato_id", nullable = false)
	private Candidato candidato;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "avaliador_id", nullable = false)
	private Avaliador avaliador;

	@OneToOne
	@JoinColumn(name = "tipoAvaliacao_id", nullable = false)
	private TipoAvaliacao tipoAvaliacao;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "prova")
	private List<ResultadoCriterio> resultadoCriterio;
	
	@OneToOne
	private ResultadoProva resultadoProva;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(byte[] avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public String getExtensaoArquivo() {
		return extensaoArquivo;
	}

	public void setExtensaoArquivo(String extensaoArquivo) {
		this.extensaoArquivo = extensaoArquivo;
	}

	public Date getDataAvaliacao() {
		return dataAvaliacao;
	}

	public void setDataAvaliacao(Date dataAvaliacao) {
		this.dataAvaliacao = dataAvaliacao;
	}

	public Candidato getCandidato() {
		return candidato;
	}

	public void setCandidato(Candidato candidato) {
		this.candidato = candidato;
	}

	public Avaliador getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Avaliador avaliador) {
		this.avaliador = avaliador;
	}

	public TipoAvaliacao getTipoAvaliacao() {
		return tipoAvaliacao;
	}

	public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
		this.tipoAvaliacao = tipoAvaliacao;
	}

	public List<ResultadoCriterio> getResultadoCriterio() {
		return resultadoCriterio;
	}

	public void setResultadoCriterio(List<ResultadoCriterio> resultadoCriterio) {
		this.resultadoCriterio = resultadoCriterio;
	}

	public ResultadoProva getResultadoProva() {
		return resultadoProva;
	}

	public void setResultadoProva(ResultadoProva resultadoProva) {
		this.resultadoProva = resultadoProva;
	}

	
}