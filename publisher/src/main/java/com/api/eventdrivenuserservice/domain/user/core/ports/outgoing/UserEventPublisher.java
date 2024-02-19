package com.api.eventdrivenuserservice.domain.user.core.ports.outgoing;

import jakarta.jms.Connection;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.jms.Session;
import jakarta.jms.MapMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;



import jakarta.jms.JMSException;

import jakarta.jms.Message;

import com.api.eventdrivenuserservice.domain.user.core.model.User;


@Component
public class UserEventPublisher {
        @Autowired
	private JmsTemplate jmsTopicTemplate;

	@Value("Q/Users/Add")
	private String topicName;



	public void publish(User user){

        // Map<String, Object> map = new HashMap<>(); 
        
        jmsTopicTemplate.send(topicName, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				System.out.println("==========SENDING PERSON " + user.getFirstName() + " TO TOPIC: \"" + topicName + " \"========== ");
				MapMessage mapMessage = session.createMapMessage();
				mapMessage.setString("firstName", user.getFirstName()); 
				mapMessage.setString("lastName", user.getLastName()); 
				mapMessage.setString("sex", user.getSex()); 
				mapMessage.setInt("age", user.getAge()); 
				return mapMessage;
			}
		});
	}
    
       
}
