package com.api.eventdrivenuserservice.domain.user.infrastructure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

@Configuration
@PropertySource({"classpath:application.properties"})
public class UserEventPublisherAdapter {

    private static final Logger logger = LoggerFactory.getLogger(UserEventPublisherAdapter.class);

    @Autowired
    private Environment environment;

    @Bean
    public SolConnectionFactory solConnectionFactory() throws Exception {
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost(environment.getProperty("solace.jms.host"));
        connectionFactory.setVPN(environment.getProperty("solace.jms.msgVpn"));
        connectionFactory.setUsername(environment.getProperty("solace.jms.clientUsername"));
        connectionFactory.setPassword(environment.getProperty("solace.jms.clientPassword"));
        return connectionFactory;
    }

    @Bean
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory ccf = new CachingConnectionFactory();
        try{
        ccf.setTargetConnectionFactory(solConnectionFactory());
        } catch (Exception e) {
            logger.info("JMS connection failed with Solace." + e.getMessage());
            e.printStackTrace();
        }
        return ccf;
    }

    @Bean
    public JmsTemplate orderJmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory());

		// By default Spring Integration uses Queues, but if you set this to true you
		// will send to a PubSub+ topic destination

		jmsTemplate.setPubSubDomain(true);

        return jmsTemplate;
    }

}
