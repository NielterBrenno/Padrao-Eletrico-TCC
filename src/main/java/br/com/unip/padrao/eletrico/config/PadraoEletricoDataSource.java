package br.com.unip.padrao.eletrico.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jndi.JndiTemplate;

@Configuration
public class PadraoEletricoDataSource {

	private static final Logger logger = LoggerFactory.getLogger(PadraoEletricoDataSource.class);

	@Profile("ds")
	@Bean
	DataSource dataSource() {
		DataSource dataSource = null;
		JndiTemplate jndi = new JndiTemplate();
		try {
			dataSource = jndi.lookup("java:comp/env/jdbc/padraoeletricods", DataSource.class);
		} catch (NamingException e) {
			logger.error("NamingException for java:comp/env/jdbc/padraoeletricods", e);
		}
		return dataSource;
	}

}
