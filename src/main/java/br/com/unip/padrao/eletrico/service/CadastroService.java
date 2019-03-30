package br.com.unip.padrao.eletrico.service;

import java.util.List;

import br.com.unip.padrao.eletrico.domain.Cliente;

public interface CadastroService {
	
	public List<Cliente> listAll();
	
	public Cliente findById(Long idCliente);
	
	public void save(Cliente cliente);

}
