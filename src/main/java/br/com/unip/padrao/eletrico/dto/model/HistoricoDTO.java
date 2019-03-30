package br.com.unip.padrao.eletrico.dto.model;

public class HistoricoDTO {

	private Long id;

	private String data;

	private Double gasto;
	
	private Double voltagem;
	
	private Double corrente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Double getGasto() {
		return gasto;
	}

	public void setGasto(Double gasto) {
		this.gasto = gasto;
	}

	public Double getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(Double voltagem) {
		this.voltagem = voltagem;
	}

	public Double getCorrente() {
		return corrente;
	}

	public void setCorrente(Double corrente) {
		this.corrente = corrente;
	}

	
	
}
