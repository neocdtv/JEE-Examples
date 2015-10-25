/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jaxwsclient;

import java.util.Collections;
import java.util.Set;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author xix
 */
public class LogicalLoggingHandler implements SOAPHandler<SOAPMessageContext>{

    private static final Logger logger = Logger.getLogger(LogicalLoggingHandler.class.getName());
    
    
    @Override
    public Set<QName> getHeaders() {
        logger.info("WWW: getHeaders");
        return Collections.emptySet();
    }

    @Override
    public boolean handleMessage(SOAPMessageContext messageContext) {                
        logger.info("WWW: handleMessage");
        Boolean outbound = (Boolean) messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (outbound != null && Boolean.TRUE.equals(outbound)) {
            final Long start = System.currentTimeMillis();
            messageContext.put("start", start);
            logger.info(String.format("WWW: outbound with address '%s'", (String)messageContext.get("javax.xml.ws.service.endpoint.address")));
        } else {
            final Long start = (Long)messageContext.get("start");
            final long end = System.currentTimeMillis();
            final long durration = end - start;
            logger.info(String.format("WWW: inbound with address '%s' time spent '%s'", (String) messageContext.get("javax.xml.ws.service.endpoint.address"), durration));
        }
        System.out.println("WWW:  Response: " + messageContext.getMessage().toString());
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        logger.info("WWW: handleFault");
        return true;
    }

    @Override
    public void close(MessageContext context) {
        logger.info("WWW: close");
    }
    
}
