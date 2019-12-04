package esgi.com.exposition.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@Configuration
public class DatabaseConfig {
	@Value ("${spring.datasource.url}")
	private String dbUrl;

	@Bean
	public DataSource dataSource () {
		com.zaxxer.hikari.HikariConfig config = new com.zaxxer.hikari.HikariConfig ();
		config.setJdbcUrl (dbUrl);
		return new HikariDataSource (config);
	}

}