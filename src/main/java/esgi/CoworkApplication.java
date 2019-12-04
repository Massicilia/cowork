package esgi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

@SpringBootApplication
public class CoworkApplication
{
	public static void main( String[] args )
	{
		Logger logger = LoggerFactory.getLogger(CoworkApplication.class);
logger.debug("datasource url : " + System.getenv("SPRING_DATASOURCE_URL"));
		SpringApplication.run(CoworkApplication.class, args);
	}
}