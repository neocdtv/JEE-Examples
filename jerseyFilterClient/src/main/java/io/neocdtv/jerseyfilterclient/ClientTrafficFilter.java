/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import org.glassfish.jersey.message.internal.ReaderWriter;

/**
 *
 * @author xix
 */
public class ClientTrafficFilter implements ClientRequestFilter, ClientResponseFilter {

    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String REQUEST_PREFIX = "-> ";
    private static final String RESPONSE_PREFIX = "<- ";

    private static final Logger logger = Logger.getLogger(ClientTrafficFilter.class.getName());

    @Override
    public void filter(final ClientRequestContext requestContext) throws IOException {
        logRequestLine(requestContext);
        logRequestHeaders(requestContext.getStringHeaders());

        if (requestContext.hasEntity()) {
            logRequestEntity(requestContext);
        }
    }

    @Override
    public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext) throws IOException {
        logResponseLine(responseContext);
        logResponseHeaders(responseContext.getHeaders());

        if (responseContext.hasEntity()) {
            logResponseEntity(responseContext);
        }
    }

    private String getEntity(final ClientResponseContext requestContext) {
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

    private void logRequestLine(final ClientRequestContext request) {
        logger.log(Level.INFO, String.format("%s%sClient request", REQUEST_PREFIX, NOTIFICATION_PREFIX));
        logger.log(Level.INFO, String.format("%s%s %s", REQUEST_PREFIX, request.getMethod(),
                request.getUri().toString()));
    }

    private void logRequestHeaders(final MultivaluedMap<String, String> headers) {
        for (Map.Entry<String, List<String>> e : headers.entrySet()) {
            final String header = e.getKey();
            for (String value : e.getValue()) {
                logger.log(Level.INFO, String.format("%s%s: %s", REQUEST_PREFIX, header, value));
            }
        }
    }

    private void logResponseLine(final ClientResponseContext response) {
        logger.log(Level.INFO, String.format("%s%sClient response", RESPONSE_PREFIX, NOTIFICATION_PREFIX));
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

    private void logRequestEntity(final ClientRequestContext requestContext) throws JsonProcessingException {
        logger.log(Level.INFO, String.format("%sEntity: %s", REQUEST_PREFIX, ClientObjectMapper.INSTANCE.writeValueAsString(requestContext.getEntity())));
    }

    private void logResponseEntity(final ClientResponseContext response) {
        logger.log(Level.INFO, String.format("%sEntity: %s", RESPONSE_PREFIX, getEntity(response)));
    }

}
