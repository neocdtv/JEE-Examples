/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */

@Provider
public class ServerExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

    private static final Logger logger = Logger.getLogger(ServerExceptionMapper.class.getName());

    @Override
    public Response toResponse(final Throwable throwable) {
        logger.log(Level.INFO, "mapping throwable to http response...");
        final Response.ResponseBuilder responseBuilder = Response.ok();
        if (throwable instanceof io.neocdtv.jarxrs.server.ValidationException) {
            io.neocdtv.jarxrs.server.ValidationException validationExcp = (io.neocdtv.jarxrs.server.ValidationException)throwable;
            responseBuilder.entity(validationExcp.getValidationConflicts());
        }
        return responseBuilder.build();
    }
}
