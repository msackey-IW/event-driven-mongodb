package com.api.eventdrivenuserservice.domain.user.infrastructure;

import org.springframework.stereotype.Component;


import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

@Component
public class UserEventPublisherAdapter {

    public SolConnectionFactory run(String... args) throws Exception {

        String[] split = args[1].split("@");

        String host = args[0];
        String vpnName = split[1];
        String username = split[0];
        String password = args[2];

        System.out.printf("QueueProducer is connecting to Solace messaging at %s...%n", host);

        // Programmatically create the connection factory using default settings
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setVPN(vpnName);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory;

   
    }

}
