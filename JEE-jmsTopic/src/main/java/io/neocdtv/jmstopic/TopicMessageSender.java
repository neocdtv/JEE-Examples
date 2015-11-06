/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmstopic;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

@ApplicationScoped
public class TopicMessageSender {
    private static final Logger LOGGER = Logger.getLogger(TopicMessageSender.class.getName());

    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/myMessageTopic")
    private Topic topic;
    
    @Inject
    JMSContext context;

    public void produceMessages() {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            try (Connection connection = connectionFactory.createConnection(); Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE)) {
                messageProducer = session.createProducer(topic);
                textMessage = session.createTextMessage();
                final String time = Long.toString(new Date().getTime());
                
                textMessage.setText(String.format("First Topic message: %s", time));
                System.out.println("Sending the following message: "
                        + textMessage.getText());
                messageProducer.send(textMessage);
                
                textMessage.setText(String.format("Second Topic message: %s", time));
                System.out.println("Sending the following message: "
                        + textMessage.getText());
                messageProducer.send(textMessage);
                
                textMessage.setText(String.format("Third Topic message: %s", time));
                System.out.println("Sending the following message: "
                        + textMessage.getText());
                messageProducer.send(textMessage);
                
                messageProducer.close();
            }
        } catch (JMSException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
