package com.rba.performance.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@Profile("dev")
public class DataSourceConfig {

	@Value("${oracle.username}")
	private String username;

	@Value("${oracle.password}")
	private String password;

	@Value("${oracle.host}")
	private String host;

	@Bean
	public HikariDataSource dataSource() {
		final String driverClassName = "oracle.jdbc.OracleDriver";
		String url = buildUrl();

		final DriverManagerDataSource driver = new DriverManagerDataSource();
		driver.setDriverClassName(driverClassName);
		driver.setUrl(url);
		driver.setUsername(username);
		driver.setPassword(password);

		final HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDataSource(driver);
		hikariConfig.setAutoCommit(true);
		hikariConfig.setMaximumPoolSize(50);
		hikariConfig.setIdleTimeout(30000);
		return new HikariDataSource(hikariConfig);
	}

	private String buildUrl() {
		final String port = "1521";
		final String sid = "xe";
		StringBuffer url = new StringBuffer();
		return url.append("jdbc:oracle:thin:@")
					.append(host)
					.append(":")
					.append(port)
					.append(":")
					.append(sid)
					.toString();
	}

}
