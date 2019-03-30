package br.com.unip.padrao.eletrico.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.com.unip.padrao.eletrico.domain.Historico;
import br.com.unip.padrao.eletrico.repository.HistoricoRepository;
import br.com.unip.padrao.eletrico.service.HistoricoService;

@Service
@Transactional
public class HistoricoServiceImpl implements HistoricoService{
	
	@Autowired
	HistoricoRepository repository;
	
	@Override
	public List<Historico> getHistoricosBy(Date date, int horas){
		LocalDate data = date.toInstant()
			  	             .atZone(ZoneId.systemDefault())
			  	             .toLocalDate();

		return repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear())
		                 .stream()
		                 .filter(h -> h.getData()
		                		 	   .toInstant()
					                   .atZone(ZoneId.systemDefault())
					                   .toLocalDateTime()
					                   .getHour() == horas)
					                   .collect(Collectors.toList());
	}

	@Override
	public List<Integer> getQuantidadeDeHistoricoPorHorasBy(Date date){
		LocalDate data = date.toInstant()
						  	 .atZone(ZoneId.systemDefault())
						  	 .toLocalDate();

		List<Integer> listSizeHistorico = new ArrayList<>();
		List<Historico> list = repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
		
		for(int i = 0; i < 24 ; i++) {
			final Integer quantidadePorHora = getTotalDeQuantidadePorHora(list, i);
			listSizeHistorico.add(quantidadePorHora != null ? quantidadePorHora : 0);
		}
		return listSizeHistorico;
	}
	
	@Override
	public Integer getQuantidadeDeHistoricoDiaroBy(Date date){
		LocalDate data = date.toInstant()
			  	 .atZone(ZoneId.systemDefault())
			  	 .toLocalDate();

		Integer listSizeHistorico = 0;
		List<Historico> list = repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
		
		for(int i = 0; i < 24 ; i++) {
		final Integer quantidadePorHora = getTotalDeQuantidadePorHora(list, i);
		listSizeHistorico += quantidadePorHora != null ? quantidadePorHora : 0;
		}
		return listSizeHistorico;
	}

	@Override
	public String getPercentualPorPeriodoBy(Date date) {
		final Integer manha = getQuantidadeDeHistoricoPorPeriodoMatutinoBy(date);
		final Integer tarde = getQuantidadeDeHistoricoPorPeriodoVespertinoBy(date);
		final Integer noite = getQuantidadeDeHistoricoPorPeriodoNoturnoBy(date);
		final Integer totalDoDia = getQuantidadeDeHistoricoDiaroBy(date);
		
		Double m = (Double.parseDouble(manha.toString()) * 100)/Double.parseDouble(totalDoDia.toString());
		Double t = (Double.parseDouble(tarde.toString()) * 100)/Double.parseDouble(totalDoDia.toString());
		Double n = (Double.parseDouble(noite.toString()) * 100)/Double.parseDouble(totalDoDia.toString());
		
		final BigDecimal percentualManha = new BigDecimal(Double.isNaN(m) ? 0.0 : m).setScale(2, RoundingMode.HALF_UP);
		final BigDecimal percentualTarde = new BigDecimal(Double.isNaN(t) ? 0.0 : t).setScale(2, RoundingMode.HALF_UP);
		final BigDecimal percentualNoite = new BigDecimal(Double.isNaN(n) ? 0.0 : n).setScale(2, RoundingMode.HALF_UP);

		String valores = percentualTarde + "," + percentualManha + "," + percentualNoite;
		return valores;
	}
	
	@Override
	public Integer getQuantidadeDeHistoricoPorPeriodoMatutinoBy(Date date){
		LocalDate data = date.toInstant()
						  	 .atZone(ZoneId.systemDefault())
						  	 .toLocalDate();

		Integer listSizeHistorico = 0;
		List<Historico> list = repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
		
		for(int i = 0; i < 12 ; i++) {
			final Integer quantidadePorHora = getTotalDeQuantidadePorHora(list, i);
			listSizeHistorico += quantidadePorHora != null ? quantidadePorHora : 0;
		}
		return listSizeHistorico;
	}
	
	@Override
	public Integer getQuantidadeDeHistoricoPorPeriodoVespertinoBy(Date date){
		LocalDate data = date.toInstant()
						  	 .atZone(ZoneId.systemDefault())
						  	 .toLocalDate();

		Integer listSizeHistorico = 0;
		List<Historico> list = repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
		
		for(int i = 12; i < 18 ; i++) {
			final Integer quantidadePorHora = getTotalDeQuantidadePorHora(list, i);
			listSizeHistorico += quantidadePorHora != null ? quantidadePorHora : 0;
		}
		return listSizeHistorico;
	}

	@Override
	public Integer getQuantidadeDeHistoricoPorPeriodoNoturnoBy(Date date){
		LocalDate data = date.toInstant()
						  	 .atZone(ZoneId.systemDefault())
						  	 .toLocalDate();

		Integer listSizeHistorico = 0;
		List<Historico> list = repository.findHistoricosPorData(data.getDayOfMonth(),data.getMonthValue(),data.getYear());
		
		for(int i = 18; i < 24 ; i++) {
			final Integer quantidadePorHora = getTotalDeQuantidadePorHora(list, i);
			listSizeHistorico += quantidadePorHora != null ? quantidadePorHora : 0;
		}
		return listSizeHistorico;
	}
	
	@Override
	public void save(Historico historico) {
		repository.saveAndFlush(historico);
	}
	
	@Override
	public Historico findById(Long idHistorico) {
		return repository.findOne(idHistorico);
	}

	@Override
	public List<Historico> listAll() {
		return repository.findAll();
	}
	
	private Integer getTotalDeQuantidadePorHora(List<Historico> list, final int i) {
		final Integer quantidadePorHora = list.stream()
								        	  .filter(h -> h.getData()
								        			  	    .toInstant()
								        			  	    .atZone(ZoneId.systemDefault())
								        			  	    .toLocalDateTime()
								        			  	    .getHour() == i)
								                  		    .collect(Collectors.toList())
								                  		    .size();
		return quantidadePorHora;
	}
	
}
