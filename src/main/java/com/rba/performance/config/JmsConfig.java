package com.rba.performance.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
@EnableJms
public class JmsConfig {

	public static final String TOPIC_ADDRESS = "TEST_TOPIC_ADDRESS";

	@Value("${spring.activemq.broker}")
	private String broker;

	@Value("${spring.activemq.user}")
	private String user;

	@Value("${spring.activemq.password}")
	private String password;

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
			@Qualifier("cachingConnectionFactory") CachingConnectionFactory connectionFactory) {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory() {
			@Override
			protected void initializeContainer(DefaultMessageListenerContainer container) {
				super.initializeContainer(container);
				container.setIdleConsumerLimit(10);
				container.setIdleTaskExecutionLimit(10);
			}
		};
		factory.setConnectionFactory(connectionFactory);
		factory.setConcurrency("10-50");
		return factory;
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(cachingConnectionFactory());
		return jmsTemplate;
	}

	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(broker);
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(activeMQConnectionFactory);
		cachingConnectionFactory.setSessionCacheSize(50);
		return cachingConnectionFactory;
	}

}
