/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.webapp;

import java.io.IOException;
import java.util.logging.Logger;
import javax.annotation.Priority;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author xix
 */
@Provider
public class JaxRsInterceptor1 implements ReaderInterceptor, WriterInterceptor {

  private static final Logger LOGGER = Logger.getLogger(JaxRsInterceptor1.class.getName());

  @Override
  public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException {
    LOGGER.info("JaxRsInterceptor1.aroundReadFrom(request)");
    return context.proceed();
  }

  @Override
  public void aroundWriteTo(WriterInterceptorContext responseContext) throws IOException {
    LOGGER.info("JaxRsInterceptor1.aroundWriteTo(response)");
    responseContext.proceed();
  }

}
