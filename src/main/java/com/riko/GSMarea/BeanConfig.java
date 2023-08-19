package com.riko.GSMarea;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {
	
	@Bean(name = "datasource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource GSMareaDataSource() {
		return DataSourceBuilder.create().build();
	}


}
