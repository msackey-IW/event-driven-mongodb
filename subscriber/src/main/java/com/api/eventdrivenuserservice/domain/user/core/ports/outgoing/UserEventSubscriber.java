package com.api.eventdrivenuserservice.domain.user.core.ports.outgoing;

import com.api.eventdrivenuserservice.domain.user.core.ports.incoming.AddNewUser;


import jakarta.jms.JMSException;
import jakarta.jms.Message;

import jakarta.jms.TextMessage;

import com.solacesystems.jms.SolJmsUtility;
import jakarta.jms.MapMessage;
import com.api.eventdrivenuserservice.domain.user.core.model.User;
import org.springframework.stereotype.Controller;

import lombok.AllArgsConstructor;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.EnableJms;

@Controller
@AllArgsConstructor
@EnableJms
public class UserEventSubscriber {
    AddNewUser addNewUser;
    
    @JmsListener(destination ="MyUsersQ")
    public void handle(Message message){
        System.out.println("Message Received!");
                try {
                    if (message instanceof MapMessage) {
                        MapMessage mapMessage = ((MapMessage) message);
                        System.out.printf("MapMessage received.");
                        // populate the db with the message
                        User user =  new User();
                        user.setFirstName(mapMessage.getString("firstName"));
                        user.setLastName(mapMessage.getString("lastName"));
                        user.setSex(mapMessage.getString("sex"));
                        user.setAge(mapMessage.getInt("age"));
                        addNewUser.handle(user);

                    } else {
                        System.out.println("Message received.");
                    }
                    System.out.printf("Message Content:%n%s%n", SolJmsUtility.dumpMessage(message));

                    // ACK the received message manually because of the set SupportedProperty.SOL_CLIENT_ACKNOWLEDGE above
                    message.acknowledge();

                } catch (JMSException ex) {
                    System.out.println("Error processing incoming message.");
                    ex.printStackTrace();
                }
            }
        

}
