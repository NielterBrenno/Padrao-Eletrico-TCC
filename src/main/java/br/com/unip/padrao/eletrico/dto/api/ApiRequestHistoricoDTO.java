package br.com.unip.padrao.eletrico.dto.api;

public class ApiRequestHistoricoDTO {

	private Long id;
	private String codregistro;
	private String gasto;
	private String voltagem;
	private String corrente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodregistro() {
		return codregistro;
	}

	public void setCodregistro(String codregistro) {
		this.codregistro = codregistro;
	}

	public String getGasto() {
		return gasto;
	}

	public void setGasto(String gasto) {
		this.gasto = gasto;
	}

	public String getVoltagem() {
		return voltagem;
	}

	public void setVoltagem(String voltagem) {
		this.voltagem = voltagem;
	}

	public String getCorrente() {
		return corrente;
	}

	public void setCorrente(String corrente) {
		this.corrente = corrente;
	}

	
	
}
