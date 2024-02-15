package com.api.eventdrivenuserservice.domain.user.core.ports.outgoing;

import javax.jms.Connection;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.DeliveryMode;

import com.api.eventdrivenuserservice.domain.user.core.model.User;
import com.api.eventdrivenuserservice.domain.user.infrastructure.UserEventPublisherAdapter;
import com.solacesystems.jms.SolConnectionFactory;

@Component
public class UserEventPublisher {
        final String QUEUE_NAME = "Q/users/add";
        final String vpnName = "default";
        final String username = "admin";

        public void publish(User user) throws Exception{
            SolConnectionFactory connectionFactory = new UserEventPublisherAdapter().run("localhost:55554", "admin@default", "admin");
            // Enables persistent queues or topic endpoints to be created dynamically
           // on the router, used when Session.createQueue() is called below
           connectionFactory.setDynamicDurables(true);
   
           // Create connection to the Solace router
           Connection connection = connectionFactory.createConnection();
   
           // Create a non-transacted, auto ACK session.
           Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
   
           System.out.printf("Connected to the Solace Message VPN '%s' with client username '%s'.%n", vpnName,
                   username);
   
           // Create the queue programmatically and the corresponding router resource
           // will also be created dynamically because DynamicDurables is enabled.
           Queue queue = session.createQueue(QUEUE_NAME);
   
           // Create the message producer for the created queue
           MessageProducer messageProducer = session.createProducer(queue);
   
           // Create a text message.
           TextMessage message = session.createTextMessage(user.toString());
    
           System.out.printf("Sending message '%s' to queue '%s'...%n", message.getText(), queue.toString());
   
           // Send the message
           // NOTE: JMS Message Priority is not supported by the Solace Message Bus
           messageProducer.send(queue, message, DeliveryMode.PERSISTENT, Message.DEFAULT_PRIORITY,
                   Message.DEFAULT_TIME_TO_LIVE);
   
           System.out.println("Sent successfully. Exiting...");
   
           // Close everything in the order reversed from the opening order
           // NOTE: as the interfaces below extend AutoCloseable,
           // with them it's possible to use the "try-with-resources" Java statement
           // see details at https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
           messageProducer.close();
           session.close();
           connection.close();
        }
       
}
