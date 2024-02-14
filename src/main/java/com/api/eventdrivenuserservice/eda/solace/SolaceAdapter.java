package com.api.eventdrivenuserservice.eda.solace;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class SolaceAdapter {
    private  SolConnectionFactory connectionFactory;
    private Connection connection;
    private  Session session;
    private MessageProducer producer;
    private  SolacePublisher solacePublisher;
    private  SolaceSubscriber solaceSubscriber;

    public SolaceAdapter(String host, String username, String password) throws JMSException {
        try {
            // Create Solace connection factory
            connectionFactory = SolJmsUtility.createConnectionFactory();

            connectionFactory.setHost(host);
            connectionFactory.setUsername(username);
            connectionFactory.setPassword(password);

            // Create connection, session, and producer
            connection = connectionFactory.createConnection();
            connection.start();
            session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            producer = session.createProducer(null); // null destination for publishing to topic
        } catch (Exception e) {
            log.error("Error while creating Solace connection", e);
        }
    }

    public void publishMessage(String topic, String message) {
        try {
            solacePublisher.publish(topic, message, producer, session);
        } catch (JMSException e) {
            log.error("Error while publishing message to Solace topic", e);
        }
    }

    public void subscribeToMessage(String topic) {
        try {
            solaceSubscriber.subscribe(topic);
        } catch (JMSException e) {
            log.error("Error while subscribing to Solace topic", e);
        }
    }

    public void close() {
        try {
            // Close resources
            if (producer != null) {
                producer.close();
            }
            if (session != null) {
                session.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (JMSException e) {
            log.error("Error while closing Solace resources", e);
        }
    }
}
