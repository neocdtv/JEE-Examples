/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmsqueue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 *
 * @author xix
 */

@ApplicationScoped
@Path("queue")
public class Rest {
    
    @Inject
    private QueueMessageSender queueMessageSender;
    
    @Inject 
    private QueueMessageProgrammaticConsumer queueMessageReceiver;
    
    
    @GET
    @Path("produce")
    public void produceMessages() {
        queueMessageSender.produceMessages();
    }
    
    @GET
    @Path("consume-programmatic")
    public void consumeMessages() {
        queueMessageReceiver.consumeMessages();
    }
}
