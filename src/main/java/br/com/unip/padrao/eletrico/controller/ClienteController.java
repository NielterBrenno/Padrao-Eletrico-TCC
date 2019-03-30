package br.com.unip.padrao.eletrico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.unip.padrao.eletrico.domain.Cliente;
import br.com.unip.padrao.eletrico.dto.api.ApiResponseClienteDTO;
import br.com.unip.padrao.eletrico.dto.model.HistoricoDTO;
import br.com.unip.padrao.eletrico.dto.model.RegistroDTO;
import br.com.unip.padrao.eletrico.service.CadastroService;

@RestController
public class ClienteController {

	@Autowired
	CadastroService cadastroService;

	@GetMapping("cadastro/listAll")
	public ResponseEntity<List<ApiResponseClienteDTO>> listAllCadastros() {
		List<Cliente> list = cadastroService.listAll();
		List<ApiResponseClienteDTO> listDTO = new ArrayList<>();

		list.forEach(l -> {
			ApiResponseClienteDTO cadastroDTO = new ApiResponseClienteDTO();
			cadastroDTO.setAtivo(l.getAtivo());
			cadastroDTO.setCgc(l.getCgc());
			cadastroDTO.setEndereco(l.getEndereco());
			cadastroDTO.setId(l.getId());
			final List<RegistroDTO> registros = new ArrayList<>();
			l.getRegistros().forEach(r -> {
				final RegistroDTO registrosDTO = new RegistroDTO();
				registrosDTO.setCodigo(r.getCodigo());
				registrosDTO.setId(r.getId());
				final List<HistoricoDTO> historicosDTO = new ArrayList<HistoricoDTO>();
				r.getHistoricos().forEach(h -> {
					final HistoricoDTO historicoDTO = new HistoricoDTO();
					historicoDTO.setData(h.getData().toString());
					historicoDTO.setGasto(h.getGasto());
					historicoDTO.setVoltagem(h.getVoltagem());
					historicoDTO.setCorrente(h.getCorrente());
					historicoDTO.setId(h.getId());
					historicosDTO.add(historicoDTO);
				});
				registrosDTO.setHistoricos(historicosDTO);
				registros.add(registrosDTO);
			});
			cadastroDTO.setRegistros(registros);
			cadastroDTO.setNome(l.getNome());
			listDTO.add(cadastroDTO);
		});

		return (listDTO == null || listDTO.isEmpty())
				? new ResponseEntity<List<ApiResponseClienteDTO>>(listDTO, HttpStatus.NO_CONTENT)
				: new ResponseEntity<List<ApiResponseClienteDTO>>(listDTO, HttpStatus.OK);
	}
	
}
