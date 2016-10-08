/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.webapp;

import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author xix
 */
@Provider
@Priority(2)
public class JaxRsFilter2 implements ContainerRequestFilter, ContainerResponseFilter {

  private static final Logger LOGGER = Logger.getLogger(JaxRsFilter2.class.getName());

  
  @Override
  public void filter(ContainerRequestContext requestContext) {
    LOGGER.info("JaxRsFilter2.filter(request)");
  }

  @Override
  public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
    LOGGER.info("JaxRsFilter2.filter(response)");
  }
  
}
