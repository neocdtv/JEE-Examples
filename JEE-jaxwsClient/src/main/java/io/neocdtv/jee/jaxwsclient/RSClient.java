/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.jaxwsclient;

import io.neocdtv.jee.jaxwsclient.service.Object1;
import io.neocdtv.jee.jaxwsclient.service.Service;
import io.neocdtv.jee.jaxwsclient.service.ServiceService;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author xix
 */

@Path("service")
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class RSClient {
    
    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/ServiceService.xml")
    private ServiceService service;
    
    @GET
    public Object1 get() {
        service.setHandlerResolver(new SOAPLoggingHandlerResolver());
        final Service servicePort = service.getServicePort();
        return servicePort.get();
    }
}
