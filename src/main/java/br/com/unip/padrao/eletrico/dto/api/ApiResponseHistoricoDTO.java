package br.com.unip.padrao.eletrico.dto.api;

public class ApiResponseHistoricoDTO {
	
	private String data;
	private Double valorMedido;
	private Double gastoMedidoKWh;
	private Double tensao;
	private Double corrente;
	private Double valorTotal;
	private String codigoRegistro;
	private String nomeCliente;
	
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public Double getValorMedido() {
		return valorMedido;
	}
	public void setValorMedido(Double valorMedido) {
		this.valorMedido = valorMedido;
	}
	public Double getGastoMedidoKWh() {
		return gastoMedidoKWh;
	}
	public void setGastoMedidoKWh(Double gastoMedidoKWh) {
		this.gastoMedidoKWh = gastoMedidoKWh;
	}
	public Double getTensao() {
		return tensao;
	}
	public void setTensao(Double tensao) {
		this.tensao = tensao;
	}
	public Double getCorrente() {
		return corrente;
	}
	public void setCorrente(Double corrente) {
		this.corrente = corrente;
	}
	public Double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(String codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public String getNomeCliente() {
		return nomeCliente;
	}
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	

}
