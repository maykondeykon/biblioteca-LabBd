package modelo;

import java.util.Date;


public class Exemplar {
	private Integer id;
	private Obra obra;
	private Date dtaAquisicao;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Obra getObra() {
		return obra;
	}

	public void setObra(Obra obra) {
		this.obra = obra;
	}

	public Date getDtaAquisicao() {
		return dtaAquisicao;
	}

	public void setDtaAquisicao(Date dtaAquisicao) {
		this.dtaAquisicao = dtaAquisicao;
	}

}
