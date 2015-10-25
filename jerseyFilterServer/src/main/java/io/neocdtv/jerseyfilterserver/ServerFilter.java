/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterserver;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import org.glassfish.jersey.message.internal.ReaderWriter;

/**
 *
 * @author xix
 */
public class ServerFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger logger = Logger.getLogger(ServerFilter.class.getName());
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        logger.info("REST request filter");
        if (requestContext.hasEntity()) {
            logger.info(getEntityBody(requestContext));
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        if (responseContext.hasEntity()) {
            logger.info(MAPPER.writeValueAsString(responseContext.getEntity()));
        }
        logger.info("REST response filter");
    }

    private String getEntityBody(ContainerRequestContext requestContext) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();

        final StringBuilder b = new StringBuilder();
        try {
            ReaderWriter.writeTo(in, out);

            byte[] requestEntity = out.toByteArray();
            if (requestEntity.length == 0) {
                b.append("").append("\n");
            } else {
                b.append(new String(requestEntity)).append("\n");
            }
            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

        } catch (IOException ex) {
            //Handle logging error
        }
        return b.toString();
    }
}
