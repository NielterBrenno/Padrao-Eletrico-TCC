package br.com.unip.padrao.eletrico.dto.model;

import java.util.List;

public class RegistroDTO {

	private Long id;

	private String codigo;

	private List<HistoricoDTO> historicos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<HistoricoDTO> getHistoricos() {
		return historicos;
	}

	public void setHistoricos(List<HistoricoDTO> historicos) {
		this.historicos = historicos;
	}

}
