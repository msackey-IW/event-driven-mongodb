package com.api.eventdrivenuserservice.eda;

import jakarta.jms.Connection;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Message;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;

import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PersonTopicPublisher {
     
    final String TOPIC_NAME = "Topic/Person/Add";
    

    public void run(String... args) throws Exception {
        String[] split = args[1].split("@");

        String host = args[0];
        String vpnName = split[1];
        String username = split[0];
        String password = args[2];

        // Send Message
        try {
			Resource resource = new ClassPathResource("MOCK_DATA.csv");
			parseCsvFile(resource, host, vpnName, username, password);
		} catch (IOException e) {

		}
        
    }

    private void parseCsvFile(Resource resource, String host, String vpnName, String username, String password) throws Exception {
        System.out.printf("PersonTopicPublisher is connecting to Solace messaging at %s...%n", host);

        // Programmatically create the connection factory using default settings
        SolConnectionFactory connectionFactory = SolJmsUtility.createConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setVPN(vpnName);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        // Create connection to the Solace router
        Connection connection = connectionFactory.createConnection();

        // Create a non-transacted, auto ACK session.
       
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        System.out.printf("Connected to the Solace Message VPN '%s' with client username '%s'.%n", vpnName,
                username);

        // Create the publishing topic programmatically
        Topic topic = session.createTopic(TOPIC_NAME);

        // Create the message producer for the created topic
        MessageProducer messageProducer = session.createProducer(topic);

        try (InputStreamReader reader = new InputStreamReader(resource.getInputStream());
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader())) {

       List<CSVRecord> csvRecords = csvParser.getRecords();

       for (CSVRecord csvRecord : csvRecords) {
           try {
               String firstName = csvRecord.get("first_name");
               String lastName = csvRecord.get("last_name");
               int age = Integer.parseInt(csvRecord.get("age"));
               String sex = csvRecord.get("sex");
               TextMessage message = session.createTextMessage(String.format("%s,%s,%d,%s", firstName,lastName,age,sex));

               System.out.printf("Sending message '%s' to topic '%s'...%n", message.getText(), topic.toString());
       
               // Send the message
               // NOTE: JMS Message Priority is not supported by the Solace Message Bus
               messageProducer.send(topic, message, DeliveryMode.PERSISTENT,
                       Message.DEFAULT_PRIORITY, Message.DEFAULT_TIME_TO_LIVE);
               System.out.println("Published Person Successfully.");
           } catch (NumberFormatException e) {

           }
       }


   }
    System.out.println("All Persons Successfully Published. Exiting ...");
        messageProducer.close();
        session.close();
        connection.close();

	}

    public static void main(String... args) throws Exception {
        // Check command line arguments
        if (args.length != 3 || args[1].split("@").length != 2) {
            System.out.println("Usage: TopicPublisher <host:port> <client-username@message-vpn> <client-password>");
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
        new PersonTopicPublisher().run(args);
    }

}
