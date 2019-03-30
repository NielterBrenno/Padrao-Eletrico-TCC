package br.com.unip.padrao.eletrico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import br.com.unip.padrao.eletrico.domain.Historico;

@Repository
public interface HistoricoRepository  extends JpaRepository<Historico, Long>{
	
	@Query("SELECT h "
		  + "FROM br.com.unip.padrao.eletrico.domain.Historico h "
		  + "JOIN FETCH h.registro r"
			+ " WHERE extract(day from h.data) = :dia"
			+ "   AND extract(month from h.data) = :mes"
			+ "   AND extract(year from h.data) = :ano"
			+ "   ORDER BY r.id, h.data"
			)
	public List<br.com.unip.padrao.eletrico.domain.Historico> findHistoricosPorData(@Param("dia") int dia, @Param("mes") int mes , @Param("ano") int ano);

}
