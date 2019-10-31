/*package com.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Configuration
@ComponentScan({ "com.*" })
@Import({ WebSecurityConfig.class })
public class AppConfig {
	@Bean(name = "dataSource")
	public DriverManagerDataSource dataSource() {
	    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	    driverManagerDataSource.setDriverClassName("oracle.jdbc.OracleDriver");
	    driverManagerDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:harjun250");
	    driverManagerDataSource.setUsername("system");
	    driverManagerDataSource.setPassword("Atram8585");
	    return driverManagerDataSource;
	}
}*/