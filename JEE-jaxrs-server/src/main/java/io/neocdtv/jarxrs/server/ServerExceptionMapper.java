/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jarxrs.server;

import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */
@Provider
public class ServerExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

  private static final Logger LOGGER = Logger.getLogger(ServerExceptionMapper.class.getName());

  @Override
  public Response toResponse(final Throwable throwable) {
    if (throwable instanceof io.neocdtv.jarxrs.server.validation.ValidationException) {
      io.neocdtv.jarxrs.server.validation.ValidationException validationExcp = (io.neocdtv.jarxrs.server.validation.ValidationException) throwable;

      if (validationExcp.hasConflicts()) {
        return Response.status(Response.Status.BAD_REQUEST).entity(validationExcp.getValidationConflicts()).build();
      } else {
        return Response.status(Response.Status.BAD_REQUEST).entity(validationExcp.getMessages()).build();
      }
    } else {
      return Response.serverError().entity(throwable.getMessage()).build();
    }
  }
}
