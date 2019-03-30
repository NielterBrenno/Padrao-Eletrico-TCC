package br.com.unip.padrao.eletrico.dto.api;

import java.util.List;

import br.com.unip.padrao.eletrico.domain.Endereco;
import br.com.unip.padrao.eletrico.dto.model.RegistroDTO;

public class ApiResponseClienteDTO {

	private Long id;

	private String nome;

	private String cgc;

	private Endereco endereco;

	private Boolean ativo;

	private List<RegistroDTO> registros;

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

	public String getCgc() {
		return cgc;
	}

	public void setCgc(String cgc) {
		this.cgc = cgc;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public List<RegistroDTO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroDTO> registros) {
		this.registros = registros;
	}


}
