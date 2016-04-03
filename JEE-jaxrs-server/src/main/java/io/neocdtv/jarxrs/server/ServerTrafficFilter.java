/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.message.internal.ReaderWriter;

/**
 *
 * @author xix
 */

@ApplicationScoped
@Provider
public class ServerTrafficFilter implements ContainerRequestFilter, ContainerResponseFilter {
    
    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String REQUEST_PREFIX = "-> ";
    private static final String RESPONSE_PREFIX = "<- ";

    private static final Logger logger = Logger.getLogger(ServerTrafficFilter.class.getName());

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logRequestLine(requestContext);
        logRequestHeaders(requestContext.getHeaders());

        if (requestContext.hasEntity()) {
            logRequestEntity(requestContext);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        logResponseLine(responseContext);
        logResponseHeaders(responseContext.getStringHeaders());

        if (responseContext.hasEntity()) {
            logResponseEntity(responseContext);
        }
    }

    private String getEntity(final ContainerRequestContext requestContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();

        final StringBuilder b = new StringBuilder();
        try {
            ReaderWriter.writeTo(in, out);

            byte[] requestEntity = out.toByteArray();
            if (requestEntity.length == 0) {
                b.append("");
            } else {
                b.append(new String(requestEntity));
            }
            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

        } catch (IOException ex) {
            //Handle logging error
        }
        return b.toString();
    }

    private void logRequestLine(final ContainerRequestContext request) {
        logger.log(Level.INFO, String.format("%s%sServer request", REQUEST_PREFIX, NOTIFICATION_PREFIX));
        logger.log(Level.INFO, String.format("%s%s %s", REQUEST_PREFIX, request.getMethod(),
                request.getUriInfo().getAbsolutePath().toString()));
    }

    private void logRequestHeaders(final MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            final String header = e.getKey();
            for (String value : e.getValue()) {
                logger.log(Level.INFO, String.format("%s%s: %s", REQUEST_PREFIX, header, value));
            }
        }
    }

    private void logResponseLine(final ContainerResponseContext response) {
        logger.log(Level.INFO, String.format("%s%sServer response", RESPONSE_PREFIX, NOTIFICATION_PREFIX));
        logger.log(Level.INFO, String.format("%sStatus %s", RESPONSE_PREFIX, response.getStatus()));
    }

    private void logResponseHeaders(final MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            final String header = e.getKey();
            for (Object value : e.getValue()) {
                logger.log(Level.INFO, String.format("%s%s: %s", RESPONSE_PREFIX, header, value));
            }
        }
    }

    private void logRequestEntity(final ContainerRequestContext requestContext) {
        logger.log(Level.INFO, String.format("%sEntity: %s", REQUEST_PREFIX, getEntity(requestContext)));
    }

    private void logResponseEntity(final ContainerResponseContext response) throws JsonProcessingException {
        logger.log(Level.INFO, String.format("%sEntity: %s", RESPONSE_PREFIX, ServerObjectMapper.INSTANCE.writeValueAsString(response.getEntity())));
    }
}
