package com.api.eventdrivenuserservice.eda.solace;

import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;

public class SolacePublisher {

    public void publish(String topic, String message, MessageProducer messageProducer, Session session) throws JMSException {
        messageProducer.send(session.createTopic(topic), session.createTextMessage(message));
    }
}
