package com.api.eventdrivenuserservice.domain.user.infrastructure;

import java.util.concurrent.CountDownLatch;



import com.solacesystems.jms.SolConnectionFactory;
import com.solacesystems.jms.SolJmsUtility;
import com.solacesystems.jms.SupportedProperty;
import org.springframework.stereotype.Component;

@Component
public class UserEventSubscriberAdapter {


    public void run(String... args)  {

    }
}
