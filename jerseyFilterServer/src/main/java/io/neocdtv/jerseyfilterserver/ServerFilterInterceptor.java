/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jerseyfilterserver;

import java.io.IOException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;

/**
 *
 * @author xix
 */
public class ServerFilterInterceptor implements ContainerRequestFilter, WriterInterceptor{

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
    }

    @Override
    public void aroundWriteTo(WriterInterceptorContext context) throws IOException, WebApplicationException {
    }
    
}
