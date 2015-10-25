/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmsqueue;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

@ApplicationScoped
public class QueueMessageSender {
    private static final Logger LOGGER = Logger.getLogger(QueueMessageSender.class.getName());

    @Resource(mappedName = "jms/myConnectionFactory")
    private ConnectionFactory connectionFactory;
    @Resource(mappedName = "jms/myMessageQueue")
    private Queue queue;

    public void produceMessages() {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            messageProducer = session.createProducer(queue);
            textMessage = session.createTextMessage();
            final String time = Long.toString(new Date().getTime());

            textMessage.setText(String.format("First message: %s", time));
            System.out.println("Sending the following message: "
                    + textMessage.getText());
            messageProducer.send(textMessage);

            textMessage.setText(String.format("Second message: %s", time));
            System.out.println("Sending the following message: "
                    + textMessage.getText());
            messageProducer.send(textMessage);

            textMessage.setText(String.format("Third message: %s", time));
            System.out.println("Sending the following message: "
                    + textMessage.getText());
            messageProducer.send(textMessage);

            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
