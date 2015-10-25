/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jmstopic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
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
}
