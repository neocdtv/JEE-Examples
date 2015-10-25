/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmstopic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 *
 * @author xix
 */
@MessageDriven(mappedName = "jms/myMessageTopic")
public class TopicMessageDrivenBeanSecond implements MessageListener{

    private static final Logger LOGGER = Logger.getLogger(TopicMessageDrivenBeanSecond.class.getName());

    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = (TextMessage)message;
            if (textMessage.getText().matches(".*Second.*")) {
                throw new RuntimeException("second message generates a exception to test the jsm transaction");
            }
            LOGGER.log(Level.INFO, String.format("MDB Second received message: %s", textMessage.getText()));
        } catch (JMSException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
}
