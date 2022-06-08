package com.rba.performance;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.test.context.ActiveProfiles;

import com.rba.performance.config.DataSourceConfig;

@SpringBootTest
@ActiveProfiles("test")
class PerformanceListenerApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	@Import({ DataSourceConfig.class })
	@Profile("test")
	public static class PerformanceDataSourceTestConfig {

		@Primary
		public DataSource dataSource() {
			final DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:mem:db;DB_CLOSE_DELAY=-1");
			dataSource.setUsername("sa");
			dataSource.setPassword("sa");
			return dataSource;
		}
	}
}
