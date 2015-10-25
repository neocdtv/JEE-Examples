/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.ContainerRequestContext;
import org.glassfish.jersey.message.internal.ReaderWriter;

/**
 *
 * @author xix
 */
public class ClientFilter implements ClientRequestFilter, ClientResponseFilter{

    private static final Logger logger = Logger.getLogger(ClientFilter.class.getName());
    public static final String PERORMANCE_OBJECT = "performanceData";
    
    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {

        final PerformanceData property = buildPerformanceData();
        requestContext.setProperty(PERORMANCE_OBJECT, property);
        logger.info(String.format("REST request, requestId '%s', url '%s'", property.getRequestId(), requestContext.getUri().toString()));
    }

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        
        final PerformanceData property = (PerformanceData)requestContext.getProperty(PERORMANCE_OBJECT);
        final long timeSpent = System.currentTimeMillis() - property.getTime();
        logger.info(String.format("REST response, requestId '%s', time '%s', url '%s'", property.getRequestId(), timeSpent, requestContext.getUri()));
        logger.info("hie");
    }
    
    private PerformanceData buildPerformanceData() {
        final long nanoTime = System.currentTimeMillis();
        final String uuid = UUID.randomUUID().toString();
        final PerformanceData performanceData = new PerformanceData();
        performanceData.setRequestId(uuid);
        performanceData.setTime(nanoTime);
        return performanceData;
    }
    
    
}
