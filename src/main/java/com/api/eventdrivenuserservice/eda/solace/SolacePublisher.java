package com.api.eventdrivenuserservice.eda.solace;

import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;

public class SolacePublisher {
    public void publish(String topic, String message, MessageProducer messageProducer, Session session) throws JMSException {
        messageProducer.send(session.createTopic(topic), session.createTextMessage(message));
    }
}
