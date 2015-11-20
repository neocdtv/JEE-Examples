/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmsqueue;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@ApplicationScoped
public class QueueMessageProgrammaticConsumer {

    private static final Logger LOGGER = Logger.getLogger(QueueMessageProgrammaticConsumer.class.getName());

    @Resource(lookup = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/myMessageQueue")
    private Queue queue;

    public void consumeMessages() {
        Connection connection;
        MessageConsumer messageConsumer;
        TextMessage textMessage;
        boolean goodByeReceived = false;
        try {
            connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            messageConsumer = session.createConsumer(queue);
            connection.start();
            while (!goodByeReceived) {
                System.out.println("Waiting for messages...");
                textMessage = (TextMessage) messageConsumer.receive();
                if (textMessage != null) {
                    System.out.print("Received the following message: ");
                    System.out.println(textMessage.getText());
                    System.out.println();
                }
                if (textMessage.getText() != null
                        && textMessage.getText().matches(".*Third.*")) {
                    goodByeReceived = true;
                }
            }
            messageConsumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
