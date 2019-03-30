package br.com.unip.padrao.eletrico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unip.padrao.eletrico.domain.Cliente;;

@Repository
public interface ClienteRepository  extends JpaRepository<Cliente, Long>{

}
