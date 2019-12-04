package esgi;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
@org.springframework.context.annotation.Configuration
public class DatabaseConfig {
	@org.springframework.beans.factory.annotation.Value ("${spring.datasource.url}")
	private String dbUrl;

	@org.springframework.context.annotation.Bean
	public javax.sql.DataSource dataSource () {
		com.zaxxer.hikari.HikariConfig config = new com.zaxxer.hikari.HikariConfig ();
		config.setJdbcUrl (dbUrl);
		return new com.zaxxer.hikari.HikariDataSource (config);
	}

}