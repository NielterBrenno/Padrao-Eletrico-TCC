package br.com.unip.padrao.eletrico.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.unip.padrao.eletrico.domain.Cliente;
import br.com.unip.padrao.eletrico.repository.ClienteRepository;
import br.com.unip.padrao.eletrico.service.CadastroService;

@Service
@Transactional
public class CadastroServiceImpl implements CadastroService{
	
	@Autowired
	ClienteRepository repository;

	@Override
	public List<Cliente> listAll() {
		return repository.findAll();
	}

	@Override
	public Cliente findById(Long idCliente) {
		return repository.findOne(idCliente);
	}

	@Override
	public void save(Cliente cliente) {
		repository.saveAndFlush(cliente);
	}

}
