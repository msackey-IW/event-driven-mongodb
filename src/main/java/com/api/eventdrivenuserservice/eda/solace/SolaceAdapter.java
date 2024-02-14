package com.api.eventdrivenuserservice.eda.solace;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SolaceAdapter {
    private SolConnectionFactory connectionFactory;
    private Connection connection;
    private Session session;
    private MessageProducer producer;
    private SolacePublisher solacePublisher;
    private SolaceSubscriber SolaceSubscriber;

    public SolaceAdapter(String host, String username, String password) throws JMSException {
        // Create Solace connection factory
        try{
            connectionFactory = SolJmsUtility.createConnectionFactory();
        }
        catch(Exception ex) {

        }
        
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        // Create connection, session, and producer
        connection = connectionFactory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null); // null destination for publishing to topic
    }

    public void publishMessage(String topic, String message) throws JMSException {
        solacePublisher.publish(topic, message, producer, session);
    }

    public void subscribeToMessage(String topic) {

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
            e.printStackTrace();
        }
    }
}