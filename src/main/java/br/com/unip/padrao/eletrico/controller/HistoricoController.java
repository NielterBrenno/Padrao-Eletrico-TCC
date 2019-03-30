package br.com.unip.padrao.eletrico.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.unip.padrao.eletrico.domain.Cliente;
import br.com.unip.padrao.eletrico.domain.Historico;
import br.com.unip.padrao.eletrico.dto.api.ApiRequestHistoricoDTO;
import br.com.unip.padrao.eletrico.dto.api.ApiResponseHistoricoDTO;
import br.com.unip.padrao.eletrico.dto.api.ApiResponseHistoricoPorRegistroDTO;
import br.com.unip.padrao.eletrico.service.CadastroService;
import br.com.unip.padrao.eletrico.service.HistoricoService;

@RestController
public class HistoricoController {
	
	@Autowired
	CadastroService cadastroService;
	
	@Autowired
	HistoricoService historicoService;
	
	@PostMapping("historico/salvar")
	public void salvarHistorico(@RequestBody ApiRequestHistoricoDTO request) {
		final Cliente cliente = cadastroService.findById(request.getId());
		cliente.getRegistros().forEach(registro -> {
			if(registro.getCodigo().equals(request.getCodregistro())){
				Historico historico = new Historico();
				historico.setGasto(Double.valueOf(request.getGasto()));
				historico.setVoltagem(Double.valueOf(request.getVoltagem()));
				historico.setData(new Date());
				historico.setRegistro(registro);
				historico.setCorrente(Double.valueOf(request.getCorrente()));
				registro.getHistoricos().add(historico);
			}
		});
		cadastroService.save(cliente);
	}
	
	@GetMapping("historico/percentual/{data}")
	public ResponseEntity<String> getPercentualPorPeriodo(@PathVariable("data") String data) {
		try {
			final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			return new ResponseEntity<String>(historicoService.getPercentualPorPeriodoBy(fmt.parse(data)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		}
	}

	
	@GetMapping("historico/list/gastos/{hora}/{data}")
	public ResponseEntity<List<ApiResponseHistoricoPorRegistroDTO>> getGastosDeHistoricosPorHora(@PathVariable("hora") int hora,
			                                                                          @PathVariable("data") String data) {
		try {
			final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			List<ApiResponseHistoricoDTO> historicosGastosDTO = getListApiResponseHistorico(hora, data, fmt);
			final List<ApiResponseHistoricoPorRegistroDTO> historicosPorRegistro = getHistoricoPorRegistro(historicosGastosDTO);
			return new ResponseEntity<List<ApiResponseHistoricoPorRegistroDTO>>
								     (historicosPorRegistro, HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<List<ApiResponseHistoricoPorRegistroDTO>>(HttpStatus.NO_CONTENT);
		}
	}
	
	@GetMapping("historico/list/quantidade/horas/{data}")
	public ResponseEntity<List<Integer>> getQuantidadeDeHistoricosPorHora(@PathVariable("data") String data) {
		try {
			final SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
			return new ResponseEntity<List<Integer>>(historicoService
								                    .getQuantidadeDeHistoricoPorHorasBy(fmt.parse(data)), HttpStatus.OK);
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<List<Integer>>(HttpStatus.NO_CONTENT);
		}
	}

	
	private List<ApiResponseHistoricoPorRegistroDTO> getHistoricoPorRegistro(List<ApiResponseHistoricoDTO> historicosGastosDTO) {
		final List<ApiResponseHistoricoPorRegistroDTO> historicosPorRegistro = new ArrayList<>();
		List<ApiResponseHistoricoDTO> historicosGastos = new ArrayList<>();
		for(int i = 0; i < historicosGastosDTO.size() ; i++) {
			final int key = i + 1;
			final ApiResponseHistoricoPorRegistroDTO historicoProRegistro = new ApiResponseHistoricoPorRegistroDTO();
			if(key < historicosGastosDTO.size()) {
				if(historicosGastosDTO.get(i).getCodigoRegistro().equals(historicosGastosDTO.get(i+1).getCodigoRegistro())) {
					historicosGastos.add(historicosGastosDTO.get(i));
				}else {
					historicoProRegistro.setNomeCliente(historicosGastosDTO.get(i).getNomeCliente());
					historicoProRegistro.setRegistro(historicosGastosDTO.get(i).getCodigoRegistro());
					historicosGastos.add(historicosGastosDTO.get(i));
					historicoProRegistro.setHistoricos(historicosGastos);
					historicosPorRegistro.add(historicoProRegistro);
					historicosGastos = new ArrayList<>();
				}
			}else {
				historicoProRegistro.setNomeCliente(historicosGastosDTO.get(i).getNomeCliente());
				historicoProRegistro.setRegistro(historicosGastosDTO.get(i).getCodigoRegistro());
				historicosGastos.add(historicosGastosDTO.get(i));
				historicoProRegistro.setHistoricos(historicosGastos);
				historicosPorRegistro.add(historicoProRegistro);
			}
		}
		return historicosPorRegistro;
	}

	private List<ApiResponseHistoricoDTO> getListApiResponseHistorico(int hora, String data, final SimpleDateFormat fmt)
			throws ParseException {
		List<Historico> historicos = historicoService.getHistoricosBy(fmt.parse(data), hora);
		List<ApiResponseHistoricoDTO> historicosGastosDTO = new ArrayList<>();
		for(int i = 0 ; i < historicos.size();i ++) {
			final ApiResponseHistoricoDTO api = new ApiResponseHistoricoDTO();
			api.setCorrente(historicos.get(i).getCorrente());
			api.setData(historicos.get(i).getData().toString());
			api.setTensao(historicos.get(i).getVoltagem());
			api.setValorMedido(historicos.get(i).getGasto());
			api.setGastoMedidoKWh(historicos.get(i).getGastoMedioEmKwh(historicos,i));
			api.setValorTotal(historicos.get(i).getValorTotal(historicos,i));
			api.setCodigoRegistro(historicos.get(i).getRegistro().getCodigo());
			api.setNomeCliente(historicos.get(i).getRegistro().getCliente().getNome());
			historicosGastosDTO.add(api);
		}
		return historicosGastosDTO;
	}
	
}
