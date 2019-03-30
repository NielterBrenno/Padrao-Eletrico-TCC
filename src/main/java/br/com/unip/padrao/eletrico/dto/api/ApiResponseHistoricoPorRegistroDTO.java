package br.com.unip.padrao.eletrico.dto.api;

import java.util.List;

public class ApiResponseHistoricoPorRegistroDTO {
	
	String nomeCliente;
	String registro;
	List<ApiResponseHistoricoDTO> historicos;

	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	public String getRegistro() {
		return registro;
	}
	public void setRegistro(String registro) {
		this.registro = registro;
	}
	public List<ApiResponseHistoricoDTO> getHistoricos() {
		return historicos;
	}
	public void setHistoricos(List<ApiResponseHistoricoDTO> historicos) {
		this.historicos = historicos;
	}

}
