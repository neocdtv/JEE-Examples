/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.jee.websocket.server;

import java.io.IOException;
import java.util.Set;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xix
 */

@Path("service")
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ServiceRS {
    
    private final Logger logger = Logger.getLogger(ServiceRS.class.getName());
    
    @Inject
    private Connections sessions;
    
    @GET
    public Set<String> sessions() {
        return sessions.getSessionIds();
    }
    
    @GET
    @Path("disconnect")
    public String disconnect() throws IOException {
        sessions.disconnect();
        return "success";
    }

    
    
}
