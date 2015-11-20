/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jaxrs.server.crosscutting;

import java.io.IOException;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author xix
 * 
 * Interceptors (implementations of ReaderInterceptor / WriterInterceptor) are
 * executed only if request/response entity is available.
 * 
 */
public class ServerInterceptor implements ReaderInterceptor, WriterInterceptor{

    private static final Logger logger = Logger.getLogger(ServerInterceptor.class.getName());
    
    @Override
    public Object aroundReadFrom(ReaderInterceptorContext readerContext) throws IOException, WebApplicationException {
        logger.info("* server response interceptor");
        return readerContext.proceed();
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext writerContext) throws IOException, WebApplicationException {
        logger.info("* server request interceptor");
        writerContext.proceed();
    }
}
