/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.webapp;

import java.util.logging.Logger;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */
@Provider
public class JaxRsExceptionMapper implements javax.ws.rs.ext.ExceptionMapper<Throwable> {

  private static final Logger LOGGER = Logger.getLogger(JaxRsExceptionMapper.class.getName());

  @Override
  public Response toResponse(final Throwable throwable) {
    LOGGER.info("JaxRsExceptionMapper.toResponse");
    return Response.serverError().entity(throwable.getMessage()).build();
  }
}
