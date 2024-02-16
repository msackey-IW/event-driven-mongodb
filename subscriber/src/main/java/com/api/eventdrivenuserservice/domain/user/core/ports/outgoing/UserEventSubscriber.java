package com.api.eventdrivenuserservice.domain.user.ports.outgoing;
import com.api.eventdrivenuserservice.domain.user.core.ports.incoming.AddNewUser;

import java.util.concurrent.CountDownLatch;
import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.concurrent.CountDownLatch;
import com.api.eventdrivenuserservice.domain.user.infrastructure.UserEventSubscriberAdapter;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import com.solacesystems.jms.SupportedProperty;
import com.api.eventdrivenuserservice.domain.user.core.model.User;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserEventSubscriber {
    // Latch used for synchronizing between threads
    final CountDownLatch latch = new CountDownLatch(1);
    private String vpnName =  "default";
    private String username = "admin";
    final String QUEUE_NAME = "Q/users/add";
    AddNewUser addNewUser;
    public void run() throws Exception{
        SolConnectionFactory connectionFactory = new UserEventSubscriberAdapter().run("localhost:55554", "admin@default", "admin");
        // Enables persistent queues or topic endpoints to be created dynamically
        // on the router, used when Session.createQueue() is called below
        connectionFactory.setDynamicDurables(true);

        // Create connection to the Solace router
        Connection connection = connectionFactory.createConnection();

        // Create a non-transacted, client ACK session.
        Session session = connection.createSession(false, SupportedProperty.SOL_CLIENT_ACKNOWLEDGE);

        System.out.printf("Connected to the Solace Message VPN '%s' with client username '%s'.%n", vpnName,
                username);

        // Create the queue programmatically and the corresponding router resource
        // will also be created dynamically because DynamicDurables is enabled.
        Queue queue = session.createQueue(QUEUE_NAME);

        // From the session, create a consumer for the destination.
        MessageConsumer messageConsumer = session.createConsumer(queue);

        // Use the anonymous inner class for receiving messages asynchronously
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        String userString = ((TextMessage) message).getText();
                        System.out.printf("TextMessage received: '%s'%n", userString);
                        // populate the db with the message
                        addNewUser.handle(fromString(userString));

                    } else {
                        System.out.println("Message received.");
                    }
                    System.out.printf("Message Content:%n%s%n", SolJmsUtility.dumpMessage(message));

                    // ACK the received message manually because of the set SupportedProperty.SOL_CLIENT_ACKNOWLEDGE above
                    message.acknowledge();

                    latch.countDown(); // unblock the main thread
                } catch (JMSException ex) {
                    System.out.println("Error processing incoming message.");
                    ex.printStackTrace();
                }
            }
        });

        // Start receiving messages
        connection.start();
        System.out.println("Awaiting message...");
        // the main thread blocks at the next statement until a message received
        latch.await();

        connection.stop();
        // Close everything in the order reversed from the opening order
        // NOTE: as the interfaces below extend AutoCloseable,
        // with them it's possible to use the "try-with-resources" Java statement
        // see details at https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        messageConsumer.close();
        session.close();
        connection.close();
    }

   // Method to create a User object from its string representation
   public User fromString(String userString) {
    User user = new User();
    // Parse the string and set the fields accordingly
    String[] parts = userString.substring(userString.indexOf("(") + 1, userString.lastIndexOf(")")).split(", ");
    for (String part : parts) {
        String[] keyValue = part.split("=");
        String key = keyValue[0].trim();
        String value = keyValue[1].trim().replaceAll("'", ""); // Remove single quotes
        switch (key) {
            case "firstName":
                user.setFirstName(value);
                break;
            case "lastName":
                user.setLastName(value);
                break;
            case "age":
                user.setAge(Integer.parseInt(value));
                break;
            case "sex":
                user.setSex(value);
            
            // Handle other fields if any
        }
    }
    return user;
    }
}
