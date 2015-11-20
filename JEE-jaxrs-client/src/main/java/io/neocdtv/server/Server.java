/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.neocdtv.server;

import io.neocdtv.client.ClientService;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author xix
 */

@Path("server")
@ApplicationScoped
public class Server {
    
    @Inject
    private ClientService service;
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() {
        return service.get().getFirst();
    }      
}
