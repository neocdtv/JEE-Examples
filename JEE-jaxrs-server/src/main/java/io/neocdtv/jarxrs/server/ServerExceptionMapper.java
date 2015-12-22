/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jaxrs.server.crosscutting;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;

/**
 *
 * @author xix
 */
public class ServerExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    private static final Logger logger = Logger.getLogger(ServerExceptionMapper.class.getName());

    @Override
    public Response toResponse(final Throwable throwable) {
        logger.log(Level.INFO, "mapping throwable to http response...");
        final Response.ResponseBuilder responseBuilder = Response.ok();
        return responseBuilder.build();
    }
}