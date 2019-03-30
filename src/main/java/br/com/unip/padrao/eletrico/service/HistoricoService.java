package br.com.unip.padrao.eletrico.service;

import java.util.Date;
import java.util.List;
import br.com.unip.padrao.eletrico.domain.Historico;

public interface HistoricoService {
	
	public List<Historico> listAll();
	
	public Historico findById(Long idHIstorico);
	
	public void save(Historico historico);
	
	public List<Historico> getHistoricosBy(Date date, int horas);
	
	public List<Integer> getQuantidadeDeHistoricoPorHorasBy(Date data);
	
	public Integer getQuantidadeDeHistoricoPorPeriodoMatutinoBy(Date date);
	
	public Integer getQuantidadeDeHistoricoPorPeriodoVespertinoBy(Date date);
	
	public Integer getQuantidadeDeHistoricoPorPeriodoNoturnoBy(Date date);
	
	public Integer getQuantidadeDeHistoricoDiaroBy(Date date);
	
	public String getPercentualPorPeriodoBy(Date date);

}
