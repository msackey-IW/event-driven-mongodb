package com.api.eventdrivenuserservice.eda;

import java.util.concurrent.CountDownLatch;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.api.eventdrivenuserservice.model.User;
import com.api.eventdrivenuserservice.service.UserService;
import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import jakarta.jms.Connection;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;


@Component
@ComponentScan(basePackages = {"com.api.eventdrivenuserservice", "com.api.eventdrivenuserservice.service"})
public class PersonTopicSubscriber {
    
    private final UserService userService;

    public PersonTopicSubscriber(UserService userService) {
        this.userService = userService;
    }

    final String TOPIC_NAME = "Topic/Person/Add";

    // Latch used for synchronizing between threads
    final CountDownLatch latch = new CountDownLatch(1);

    public void run(String... args) throws Exception {
        String[] split = args[1].split("@");

        String host = args[0];
        String vpnName = split[1];
        String username = split[0];
        String password = args[2];

        System.out.printf("TopicSubscriber is connecting to Solace messaging at %s...%n", host);

        // Programmatically create the connection factory using default settings
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setVPN(vpnName);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        Connection connection = connectionFactory.createConnection();


        // Create a non-transacted, Auto ACK session.
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.printf("Connected to Solace Message VPN '%s' with client username '%s'.%n", vpnName,
                username);

        // Create the subscription topic programmatically
        Topic topic = session.createTopic(TOPIC_NAME);

        // Create the message consumer for the subscription topic
        MessageConsumer messageConsumer = session.createConsumer(topic);

        // Use the anonymous inner class for receiving messages asynchronously
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        String myMessage = ((TextMessage) message).getText();
                        String[] userDetails =  (((TextMessage)message).getText()).split(",", 6);
                        User user =  new User(
                            userDetails[0],
                            userDetails[1],
                            Integer.valueOf(userDetails[2]),
                            userDetails[3]
                        );
                        userService.addUser(user);
                        System.out.printf("TextMessage received: '%s'%n ", myMessage);
                        System.out.println(userDetails[0] + " successfully added to database.");
                    } else {
                        System.out.println("Message received.");
                    }
                    
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
        PersonTopicPublisher publisher = new PersonTopicPublisher();
		publisher.run("host.docker.internal:55554", "admin@default", "admin");
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

    public static void main(String... args) throws Exception {
        if (args.length != 3 || args[1].split("@").length != 2) {
            System.out.println("Usage: TopicSubscriber <host:port> <client-username@message-vpn> <client-password>");
            System.out.println();
            System.exit(-1);
        }
        if (args[1].split("@")[0].isEmpty()) {
            System.out.println("No client-username entered");
            System.out.println();
            System.exit(-1);
        }
        if (args[1].split("@")[1].isEmpty()) {
            System.out.println("No message-vpn entered");
            System.out.println();
            System.exit(-1);
        }
       
    }
}
