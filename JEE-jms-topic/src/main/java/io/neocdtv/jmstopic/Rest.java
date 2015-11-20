/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmstopic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author xix
 */

@ApplicationScoped
@Path("topic")
public class Rest {
    
    @Inject
    private TopicMessageSender topicMessageSender;
    
    @Inject
    private TopicMessageProgrammaticConsumer topicMessageProgrammaticConsumer;
    
    @GET
    @Path("produce")
    public void produceMessages() {
        topicMessageSender.produceMessages();
    }
    
    @GET
    @Path("consume-programmatic")
    public void consumeMessages() {
        topicMessageProgrammaticConsumer.consumeMessages();
    }
    
    @GET
    @Path("dead-messages")
    public String listDeadMessageQueue() throws NamingException, JMSException {
        InitialContext ctx = new InitialContext();
        ctx.lookup("jms/mq.sys.dmq");
        return "bla";
    }
}
